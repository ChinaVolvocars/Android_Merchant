package com.hzxmkuar.sxmaketnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.AddPicBean;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.methods.UploadMethods;
import com.common.retrofit.uploadfile.FileUploadObserver;
import com.common.utils.DeviceUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.FileUtils;
import com.common.utils.LQRPhotoSelectUtils;
import com.common.utils.PhotoUtils;
import com.common.utils.SoftHideKeyBoardUtil;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hzxmkuar.sxmaketnew.common.PictureCheckDialogFragment;
import com.hzxmkuar.sxmaketnew.common.photoPcker.MQPhotoPickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by STH on 2017/10/30.
 */

public class PicSetActivity extends BaseMvpActivity {
    private TextView btnRight;
    private ListView listView;
    private boolean is_over = false;
    private ImageView mBack;
    private int mNum;
    private List<AddPicBean> mBeanList;
    private Map<Integer,String> mMappic = new HashMap<>();
    private Map<Integer,String> mMapdes = new HashMap<>();
    private List<AddPicBean> mAddPicBeanList = new ArrayList<>();
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private String path;
    private PictureCheckDialogFragment dialogFragment;
    private int mMyt;
    private RecyclerView mRecyclerView;
    private ImageView mFace;

    @Override
    protected void setStatusBar() {
    }
    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        SoftHideKeyBoardUtil.assistActivity((Activity) context);
        return R.layout.activity_gift_set;
    }

    @Override
    protected void onViewCreated() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        btnRight = (TextView) findViewById(R.id.yes);
        mBack = (ImageView) findViewById(R.id.back);
        init();
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mBack);
        attachClickListener(btnRight);
        mNum = getIntent().getIntExtra("num",0);
        for (int t=0 ;t<mNum ;t++ ){
            mMappic.put(t,"");
            mMapdes.put(t,"");
        }
        String name = getIntent().getStringExtra("name");
        mBeanList = new Gson().fromJson(name,new TypeToken<List<AddPicBean>>(){}.getType());
        if (mNum>mBeanList.size()){
            for (int i = 0 ; i<mNum-mBeanList.size();i++){
                mBeanList.add(new AddPicBean("",""));
            }
        }
        setOneType();
    }
    private void setOneType() {
        //设置布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(context,1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //设置适配器
        showProgressingDialog();
        VideoAdapter hotOneAdapter  = new VideoAdapter(context);
        mRecyclerView.setAdapter(hotOneAdapter);
    }
    private void init() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
                uploadFile(outputUri.getPath());
            }
        }, false);//true裁剪，false不裁剪
    }
    @Override
    protected void onViewClicked(View view) {
         if (view.getId()==btnRight.getId()){
             for (int t = 0;t<mNum ; t++){
                 if (mMappic.get(t).equals("")){
                     showToastMsg("照片填写不完整");
                     return;
                 }
                 if (mMapdes.get(t).equals("")){
                     showToastMsg("内容填写不完整");
                     return;
                 }
                 mAddPicBeanList.add(new AddPicBean(mMappic.get(t),mMapdes.get(t)));
             }
             String json = new Gson().toJson(mAddPicBeanList);
             System.out.println("aaaaaa"+json);
             Intent intent = new Intent();
             intent.putExtra("name",json);
             setResult(10,intent);
             finish();
        }else if (view.getId()==mBack.getId()){
             finish();
         }
    }

    class VideoAdapter extends
            RecyclerView.Adapter<VideoAdapter.ViewHolder>
    {
        private LayoutInflater mInflater;
        private Context mContext;

        public VideoAdapter(Context context)
        {
            mInflater = LayoutInflater.from(context);
            mContext =context;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public ViewHolder(View arg0)
            {
                super(arg0);
            }
            ImageView face ;
            DeleteEditText name ;
        }

        @Override
        public int getItemCount()
        {
            return mNum;
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i)
        {
            View view = mInflater.inflate(R.layout.item_choose_gift,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.face = (ImageView) view.findViewById(R.id.pic);
            viewHolder.name = (DeleteEditText) view.findViewById(R.id.des);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            if (mBeanList.size()>i){
                if (mBeanList.get(i).getShop_pic().equals("")){
                    viewHolder.face.setImageResource(R.mipmap.add_pic);
                }else {
                    ImageLoaderUtils.displaySmallPhoto(viewHolder.face,mBeanList.get(i).getShop_pic());
                }
                viewHolder.name.setText(mBeanList.get(i).getShop_description());
                mMapdes.put(i,mBeanList.get(i).getShop_description());
                mMappic.put(i,mBeanList.get(i).getShop_pic());
            }
            dismissProgressDialog();
            viewHolder.face.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMyt = i;
                    mFace = viewHolder.face;
                    showPicCheck();
                }
            });
            viewHolder.name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mMapdes.put(i,editable.toString());
                }
            });
        }
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
        try {
            startActivityForResult(MQPhotoPickerActivity.newIntent(context, null, 1, null,
                    getString(R.string.mq_send)), Constants.REQUEST_CODE_PHOTO);
        } catch (Exception e) {
            showToastMsg("当前设备不支持发送图片");
        }
    }
    public void selectpicfromsys() {
        mLqrPhotoSelectUtils.takePhoto();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 10001) {
                mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
            } else if (requestCode == Constants.REQUEST_CODE_PHOTO) {
                // 从 相册 获取的图片
                ArrayList<String> selectedPhotos = MQPhotoPickerActivity.getSelectedImages(data);
                if (EmptyUtils.isEmpty(selectedPhotos)) {
                    showToastMsg("尚未选择图片");
                    return;
                }
                path = selectedPhotos.get(0);
                uploadFile(path);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void uploadFile(final String imageUrl) {
        if (EmptyUtils.isEmpty(imageUrl)) {
            showToastMsg("请选择图片");
            return;
        }
        System.out.println("aaaaaaaaaaa"+imageUrl);
        showProgressingDialog();

        final Bitmap bitmap;
        try {
            bitmap = PhotoUtils.revitionImageSize(imageUrl);
            FileUploadObserver<PicBean> subscriber = new FileUploadObserver<PicBean>() {
                @Override
                public void onUpLoadSuccess(PicBean s) {
                    dismissProgressDialog();
                    mMappic.put(mMyt,s.getData());
                    ImageLoaderUtils.displaySmallPhoto(mFace,s.getData());
                    init();
                }

                @Override
                public void onUpLoadFail(Throwable e) {
                    dismissProgressDialog();
                }

                @Override
                public void onProgress(int progress) { }
            };
            UploadMethods.getInstance().photoUpload(subscriber, FileUtils.saveBitmap(bitmap, DeviceUtils.getUUID()));
            rxManager.add(subscriber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
