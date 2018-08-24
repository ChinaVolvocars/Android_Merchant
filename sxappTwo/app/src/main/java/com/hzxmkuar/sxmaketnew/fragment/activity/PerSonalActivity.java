package com.hzxmkuar.sxmaketnew.fragment.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.utils.ResourceUtils;
import com.common.widget.dialog.BaseBottomDialog;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.common.PictureCheckDialogFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by STH on 2018/3/30.
 */

public class PerSonalActivity extends BaseMvpActivity {

    private String path;
    private ImageView mFace,mBack;
    private TextView mName;
    private PictureCheckDialogFragment dialogFragment;
    private Map<String, String> mMap = new HashMap<>();
    private String mId = "";
    private String mtoken = "";
    private Uri contentUri;
    private TextView mSex;
    private TextView mAge;
    private TextView mPhone;
    private LinearLayout mLl_name;
    private LinearLayout mLl_sex;
    private LinearLayout mLl_age;
    private TimePickerView pvCustomTime;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void onViewCreated() {
        mFace = (ImageView) findViewById(R.id.face);
        mBack = (ImageView) findViewById(R.id.back);
        mName = (TextView) findViewById(R.id.name);
        mSex = (TextView) findViewById(R.id.sex);
        mAge = (TextView) findViewById(R.id.age);
        mPhone = (TextView) findViewById(R.id.phone);
        mLl_name = (LinearLayout) findViewById(R.id.ll_name);
        mLl_sex = (LinearLayout) findViewById(R.id.ll_sex);
        mLl_age = (LinearLayout) findViewById(R.id.ll_age);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mFace);
        attachClickListener(mBack);
        attachClickListener(mName);
        attachClickListener(mSex);
        attachClickListener(mAge);
        attachClickListener(mLl_name);
        attachClickListener(mLl_sex);
        attachClickListener(mLl_age);
        initCustomTimePicker();
    }
    @Override
    protected void onViewClicked(View view) {
        if (view.getId()==mBack.getId()){
            finish();
        }else if (view.getId()==mFace.getId()){
            showPicCheck();
        }else if (view.getId()==mName.getId()){
            gotoActivity(NameSettingActivity.class);
        }else if (view.getId()==mLl_name.getId()){
            gotoActivity(NameSettingActivity.class);
        }else if (view.getId()==mSex.getId()){
            showDialogInfo();
        }else if (view.getId()==mLl_sex.getId()){
            showDialogInfo();
        }else if (view.getId()==mAge.getId()){
            pvCustomTime.show(); //弹出自定义时间选择器
        }else if (view.getId()==mLl_age.getId()){
            pvCustomTime.show(); //弹出自定义时间选择器
        }else if (view.getId() == tvTakePic.getId()) {
            sexDialog.dismiss();
            mSex.setText("男");
        } else if (view.getId() == tvAlbum.getId()) {
            sexDialog.dismiss();
            mSex.setText("女");
        } else if (view.getId() == tvCancel.getId()) {
            sexDialog.dismiss();
        }
    }
    private void initCustomTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1949, 10, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2018, 5, 1);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mAge.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(TimePickerView.Type.ALL)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();

    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String s = format.format(date);
        String ss = format.format(new Date(System.currentTimeMillis()));
        int i = Integer.parseInt(ss) - Integer.parseInt(s);
        return i+"";
    }
    private BaseBottomDialog sexDialog;
    private TextView tvTakePic;
    private TextView tvAlbum;
    private TextView tvCancel;

    private void showDialogInfo()
    {
        sexDialog = BaseBottomDialog.newInstance(R.layout.base_bottom_avater_view);
        sexDialog.show(getSupportFragmentManager(), "sexDialog");
        getSupportFragmentManager().executePendingTransactions();
        tvTakePic = (TextView) sexDialog.getDialog().findViewById(R.id.tv_takepic);
        tvAlbum = (TextView) sexDialog.getDialog().findViewById(R.id.tv_album);
        tvCancel = (TextView) sexDialog.getDialog().findViewById(R.id.tv_cancel);
        tvTakePic.setTextColor(ResourceUtils.getColor(context, R.color.selector_base_color));
        tvAlbum.setTextColor(ResourceUtils.getColor(context, R.color.selector_base_color));
        tvTakePic.setText("男");
        tvAlbum.setText("女");
        attachClickListener(tvTakePic);
        attachClickListener(tvAlbum);
        attachClickListener(tvCancel);
    }
    // 图片选择界面
    private void showPicCheck() {
        dialogFragment = new PictureCheckDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "PictureCheckDialogFragment");
        getFragmentManager().executePendingTransactions();
        dialogFragment.setTakePhotoListener(new PictureCheckDialogFragment.onTakePhotoListener() {
            @Override
            public void takePhoto() { selectpicfromsys(); }

            @Override
            public void takePicker() { selectpic(); }
        });
    }
    public void selectpic() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        //选择的格式为视频,图库中就只显示视频（如果图片上传的话可以改为image/*，图库就只显示图片）
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, 1);
    }
    public void selectpicfromsys() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri mImageCaptureUri;
        // 判断7.0android系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //临时添加一个拍照权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //通过FileProvider获取uri
            contentUri = FileProvider.getUriForFile(PerSonalActivity.this,
                    "com.hzxmkuar.sxmaket.fileProvider",  new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            startActivityForResult(intent, 2);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if(requestCode==2){
                Uri pictur = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/temp.jpg"));
                path = pictur.getPath();
            }else if(requestCode==1){
                getRealFilePath(PerSonalActivity.this,data.getData());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /* 下面是4.4后通过Uri获取路径以及文件名一种方法，比如得到的路径 /storage/emulated/0/video/20160422.3gp，
                                通过索引最后一个/就可以在String中截取了*/
    public void getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
                if (null != cursor) {
                    if (cursor.moveToFirst()) {
                        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                        if (index > -1) {
                            data = cursor.getString(index);
                        }
                    }
                    cursor.close();
                }
            }
        showProgressingDialog();
        path = data;
    }
}
