package com.hyphenate.easeui.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.common.utils.DeviceUtils;
import com.common.utils.EmptyUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.domain.EaseEmojicon;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EaseUI {
    private static final String TAG = EaseUI.class.getSimpleName();

    /** the global EaseUI instance */
    private static EaseUI instance = null;
    
    /** user profile provider */
    private EaseUserProfileProvider userProvider;
    
    private EaseSettingsProvider settingsProvider;
    private UserProfileManager userProManager;

    /** application context */
    private Context appContext = null;
    
    /**
     * init flag: test if the sdk has been inited before, we don't need to init again
     */
    private boolean sdkInited = false;
    
    /** the notifier */
    private EaseNotifier notifier = null;
    
    /** save foreground Activity which registered eventlistener */
    private List<Activity> activityList = new ArrayList<Activity>();
    
    public void pushActivity(Activity activity){
        if(!activityList.contains(activity)){
            activityList.add(0,activity); 
        }
    }
    
    public void popActivity(Activity activity){
        activityList.remove(activity);
    }

    /** data sync listener */
    public interface DataSyncListener {
        /**
         * sync complete
         * @param success true：data sync successful，false: failed to sync data
         */
        void onSyncComplete(boolean success);
    }

    private EaseUI(){}
    
    /**
     * get instance of EaseUI
     * @return
     */
    public synchronized static EaseUI getInstance(){
        if(instance == null){
            instance = new EaseUI();
        }
        return instance;
    }

    /**
     * init helper
     * @param context
     *  application context
     */
    public void init(Context context)
    {
        EMOptions options = initChatOptions();
        //use default options if options is null
        if (EaseUI.getInstance().init(context, options)) {
            appContext = context;

            //debug mode, you'd better set it to false, if you want release your App officially.
            EMClient.getInstance().setDebugMode(true);
            //get easeui instance
            instance = EaseUI.getInstance();
            //initialize preference manager
            PreferenceManager.init(context);
            //initialize profile manager
            getUserProfileManager().init(context);
        }
    }

    /**
     *this function will initialize the SDK and easeUI kit
     * 
     * @return boolean true if caller can continue to call SDK related APIs after calling onInit, otherwise false.
     * 
     * @param context
     * @param options use default if options is null
     * @return
     */
    public synchronized boolean init(Context context, EMOptions options){
        if(sdkInited){
            return true;
        }
        appContext = context;
        
        int pid = android.os.Process.myPid();
        String processAppName = DeviceUtils.getAppName(pid);
        
        Log.d(TAG, "process app name : " + processAppName);

        // if there is application has remote service, application:onCreate() maybe called twice
        // this check is to make sure SDK will initialized only once
        // return if process name is not application's name since the package name is the default process name
        if (processAppName == null || !processAppName.equalsIgnoreCase(appContext.getPackageName())) {
            Log.e(TAG, "enter the service process!");
            return false;
        }
        if(options == null){
            EMClient.getInstance().init(context, initChatOptions());
        }else{
            EMClient.getInstance().init(context, options);
        }
        
        initNotifier();
        registerMessageListener();
        if(userProvider == null){
            userProvider = new EaseUserProfileProvider() {
                @Override
                public EaseUser getUser(String username) {
                    return getUserInfo(username);
                }
            };
        }
        if(settingsProvider == null){
            settingsProvider = new DefaultSettingsProvider();
        }
        
        sdkInited = true;
        return true;
    }

    private EaseUser getUserInfo(String username){
        // To get instance of EaseUser, here we get it from the user list in memory
        // You'd better cache it if you get it from your server
        EaseUser user = null;
        if(username.equals(EMClient.getInstance().getCurrentUser()))
            return getUserProfileManager().getCurrentUserInfo();

        // if user is not in your contacts, set inital letter for him/her
        if(user == null){
            user = new EaseUser(username);
            EaseCommonUtils.setUserInitialLetter(user);
        }
        return user;
    }

    /**
     * if ever logged in
     * @return
     */
    public boolean isLoggedIn() {
        return EMClient.getInstance().isLoggedInBefore();
    }

    public UserProfileManager getUserProfileManager() {
        if (userProManager == null) {
            userProManager = new UserProfileManager();
        }
        return userProManager;
    }

    protected EMOptions initChatOptions(){
        Log.d(TAG, "init HuanXin Options");

        EMOptions options = new EMOptions();
        // change to need confirm contact invitation
        options.setAcceptInvitationAlways(false);
        // set if need read ack
        options.setRequireAck(true);
        // set if need delivery ack
        options.setRequireDeliveryAck(false);
        
        return options;
    }
    
    void initNotifier(){
        notifier = createNotifier();
        notifier.init(appContext);
    }
    
    private void registerMessageListener() {
        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                EaseAtMessageHelper.get().parseMessages(messages);

                if (EmptyUtils.isNotEmpty(messages)) {

                    Intent intent = new Intent("MSG_ACTION");
                    intent.putExtra("isVisible", true);
                    appContext.sendBroadcast(intent);

                    //NotificationUtils.showNotification(appContext, appContext.getString(R.string.app_name),"收到一条消息");
                }
            }
            @Override
            public void onMessageRead(List<EMMessage> messages) {}
            @Override
            public void onMessageDelivered(List<EMMessage> messages) {}
            @Override
            public void onMessageChanged(EMMessage message, Object change) {}
            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {}
        });
    }

    protected EaseNotifier createNotifier(){
        return new EaseNotifier();
    }
    
    public EaseNotifier getNotifier(){
        return notifier;
    }
    
    public boolean hasForegroundActivies(){
        return activityList.size() != 0;
    }
    
    /** set user profile provider */
    public void setUserProfileProvider(EaseUserProfileProvider userProvider){
        this.userProvider = userProvider;
    }
    
    /**
     * get user profile provider
     * @return
     */
    public EaseUserProfileProvider getUserProfileProvider(){
        return userProvider;
    }
    
    public void setSettingsProvider(EaseSettingsProvider settingsProvider){
        this.settingsProvider = settingsProvider;
    }
    
    public EaseSettingsProvider getSettingsProvider(){
        return settingsProvider;
    }
    
    /**
     * User profile provider
     * @author wei
     *
     */
    public interface EaseUserProfileProvider {
        /**
         * return EaseUser for input username
         * @param username
         * @return
         */
        EaseUser getUser(String username);
    }
    
    /**
     * Emojicon provider
     *
     */
    public interface EaseEmojiconInfoProvider {
        /**
         * return EaseEmojicon for input emojiconIdentityCode
         * @param emojiconIdentityCode
         * @return
         */
        EaseEmojicon getEmojiconInfo(String emojiconIdentityCode);
        
        /**
         * get Emojicon map, key is the text of emoji, value is the resource id or local path of emoji icon(can't be URL on internet)
         * @return
         */
        Map<String, Object> getTextEmojiconMapping();
    }
    
    private EaseEmojiconInfoProvider emojiconInfoProvider;
    
    /**
     * Emojicon provider
     * @return
     */
    public EaseEmojiconInfoProvider getEmojiconInfoProvider(){
        return emojiconInfoProvider;
    }
    
    /**
     * set Emojicon provider
     * @param emojiconInfoProvider
     */
    public void setEmojiconInfoProvider(EaseEmojiconInfoProvider emojiconInfoProvider){
        this.emojiconInfoProvider = emojiconInfoProvider;
    }
    
    /**
     * new message options provider
     *
     */
    public interface EaseSettingsProvider {
        boolean isMsgNotifyAllowed(EMMessage message);
        boolean isMsgSoundAllowed(EMMessage message);
        boolean isMsgVibrateAllowed(EMMessage message);
        boolean isSpeakerOpened();
    }
    
    /**
     * default settings provider
     *
     */
    protected class DefaultSettingsProvider implements EaseSettingsProvider{

        @Override
        public boolean isMsgNotifyAllowed(EMMessage message) {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isMsgSoundAllowed(EMMessage message) {
            return true;
        }

        @Override
        public boolean isMsgVibrateAllowed(EMMessage message) {
            return true;
        }

        @Override
        public boolean isSpeakerOpened() {
            return true;
        } 
    }
    
    public Context getContext(){
        return appContext;
    }
}
