package com.hzxmkuar.sxmaketnew.activity;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.InfoBean;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.methods.UploadMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
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
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.common.PictureCheckDialogFragment;
import com.hzxmkuar.sxmaketnew.common.photoPcker.MQPhotoPickerActivity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  上传商户店铺照片
 * Created by little_bug on 2018/10/15.
 */
public class UploadPicAndDescActivity extends BaseMvpActivity {
    private static final String TAG = "PicSetActivity";
    private TextView btnRight;
    private ImageView mBack;
    private int mImagTotal;
    /**
     * 上个界面传过来的，所有的图片与描述的集合
     */
    private List<InfoBean.ShopPicBean> mPicAndDescList = new ArrayList<>();
    /**
     * 用以上传的图片及描述的集合
     */
    private List<InfoBean.ShopPicBean> uploadingPicAndDescList = new ArrayList<>();
    /**
     * 图片集合
     */
    private Map<Integer, String> mPicMap = new HashMap<>();
    /**
     * 图片描述集合
     */
    private Map<Integer, String> mDescMap = new HashMap<>();

    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private String path;
    private PictureCheckDialogFragment dialogFragment;
    private int mClickItemPossition;
    private RecyclerView mRecyclerView;
    private ImageView mFace;
    private ImageListAdapter imageListAdapter;

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
        initImagePicker();
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mBack);
        attachClickListener(btnRight);
        mImagTotal = getIntent().getIntExtra("num", 0);
        String name = getIntent().getStringExtra("name");
        mPicAndDescList = new Gson().fromJson(name, new TypeToken<List<InfoBean.ShopPicBean>>() {
        }.getType());

        for (int t = 0; t < mPicAndDescList.size(); t++) {
            mDescMap.put(t, mPicAndDescList.get(t).getShop_description());
            mPicMap.put(t, mPicAndDescList.get(t).getShop_pic());
        }
        initAdapter();
    }

    /**
     *  初始化适配器
     */
    private void initAdapter() {
        //设置布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(context, 1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //设置适配器
        showProgressingDialog();
        imageListAdapter = new ImageListAdapter(context);
        mRecyclerView.setAdapter(imageListAdapter);
    }

    /**
     *  初始化图片选择器
     */
    private void initImagePicker() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
                updatePicOnItemView(outputUri.getPath());
            }
        }, false);//true裁剪，false不裁剪
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnRight.getId()) {
            updateShopImgs();
        } else if (view.getId() == mBack.getId()) {
            finish();
        }
    }

    /**
     * 上传图片
     */
    private void updateShopImgs() {
        for (int t = 0; t < mPicAndDescList.size(); t++) {
            if (!(EmptyUtils.isEmpty(mDescMap.get(t))) && EmptyUtils.isEmpty(mPicMap.get(t))) {
                showToastMsg("照片填写不完整");
                return;
            }
            InfoBean.ShopPicBean uploadEntity = new InfoBean.ShopPicBean();
            uploadEntity.setId(mPicAndDescList.get(t).getId());
            uploadEntity.setShop_id(mPicAndDescList.get(t).getShop_id());
            uploadEntity.setShop_pic(mPicMap.get(t));
            uploadEntity.setShop_description(mDescMap.get(t));
            uploadingPicAndDescList.add(uploadEntity);
        }
        String picsList = new Gson().toJson(uploadingPicAndDescList);
        Intent intent = new Intent();
        intent.putExtra("name", picsList);
        setResult(10, intent);

        showProgressingDialog();
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                showToastMsg("提交成功");
                finish();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        List<String> commitInfoList = new ArrayList<>();
        commitInfoList.add("time");
        commitInfoList.add("hashid");
        commitInfoList.add("uid");
        commitInfoList.add("shop_pic");
        BusinessUserMethods.getInstance().updateShopImgs(subscriber, picsList, commitInfoList);
        rxManager.add(subscriber);
    }


    class ImageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private LayoutInflater mInflater;
        private Context mContext;
//        private boolean isFirstEnter ;
        /**
         * 正常条目
         */
        private int NORMAL_ITEM = 0;
        /**
         * 脚布局
         */
        private int FOOT_ITEM = 1;

        public ImageListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            mContext = context;
        }


        @Override
        public int getItemCount() {
            return mImagTotal;
        }

        @Override
        public int getItemViewType(int position) {
            if (position + 1 == getItemCount()) { //代表item 底部
                return FOOT_ITEM;
            }
            return NORMAL_ITEM;

        }

        /**
         * 创建ViewHolder
         */
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
            View view = null;
            if (viewType == FOOT_ITEM) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_footview, parent, false);
                FootViewHolder footViewHolder = new FootViewHolder(view);
                footViewHolder.btnConfirmUpdate = (Button) view.findViewById(R.id.btn_confirm_upload);
                return footViewHolder;
            } else {
                view = mInflater.inflate(R.layout.item_choose_gift,
                        parent, false);
                NormalItemViewHolder viewHolder = new NormalItemViewHolder(view);
                viewHolder.ivFace = (ImageView) view.findViewById(R.id.pic);
                viewHolder.edtImageDesc = (DeleteEditText) view.findViewById(R.id.des);
                return viewHolder;
            }

        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int possition) {
            if (viewHolder instanceof NormalItemViewHolder) {
                final NormalItemViewHolder normalItemViewHolder = (NormalItemViewHolder) viewHolder;
                viewHolder.setIsRecyclable(false);
                normalItemViewHolder.edtImageDesc.setText(mDescMap.get(possition));
                normalItemViewHolder.edtImageDesc.setSelection((normalItemViewHolder.edtImageDesc).getText().toString().length());

                if ("".equals(mPicMap.get(possition))) {
                    normalItemViewHolder.ivFace.setImageResource(R.mipmap.chose_shop_imgs_bg);
                } else {
                    ImageLoaderUtils.displaySmallPhoto(normalItemViewHolder.ivFace, mPicMap.get(possition));
                }
                dismissProgressDialog();
                normalItemViewHolder.ivFace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mClickItemPossition = possition;
                        mFace = normalItemViewHolder.ivFace;
                        showPicCheck();
                    }
                });
                normalItemViewHolder.edtImageDesc.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        mDescMap.put(possition, editable.toString());

                    }
                });
            } else if (viewHolder instanceof FootViewHolder) {
                FootViewHolder footViewHolder = (FootViewHolder) viewHolder;
                footViewHolder.btnConfirmUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateShopImgs();
                    }
                });
            }
        }

        class FootViewHolder extends RecyclerView.ViewHolder {

            public FootViewHolder(View itemView) {
                super(itemView);
            }

            Button btnConfirmUpdate;
        }

        class NormalItemViewHolder extends RecyclerView.ViewHolder {
            public NormalItemViewHolder(View arg0) {
                super(arg0);
            }

            ImageView ivFace;
            DeleteEditText edtImageDesc;
        }
    }

    // 图片选择界面
    private void showPicCheck() {
        dialogFragment = new PictureCheckDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "PictureCheckDialogFragment");
        getFragmentManager().executePendingTransactions();
        dialogFragment.setTakePhotoListener(new PictureCheckDialogFragment.onTakePhotoListener() {
            @Override
            public void takePhoto() {
                selectpicfromsys();
            }

            @Override
            public void takePicker() {
                selectpic();
            }
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
                updatePicOnItemView(path);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     *  选择图片更新到item上
     * @param imageUrl
     */
    private void updatePicOnItemView(final String imageUrl) {
        if (EmptyUtils.isEmpty(imageUrl)) {
            showToastMsg("请选择图片");
            return;
        }
        showProgressingDialog();
        final Bitmap bitmap;
        try {
            bitmap = PhotoUtils.revitionImageSize(imageUrl);
            FileUploadObserver<PicBean> subscriber = new FileUploadObserver<PicBean>() {
                @Override
                public void onUpLoadSuccess(PicBean picBean) {
                    dismissProgressDialog();
                    mPicMap.put(mClickItemPossition, picBean.getData());
                    ImageLoaderUtils.displaySmallPhoto(mFace, picBean.getData());
//                    initImagePicker();
                }

                @Override
                public void onUpLoadFail(Throwable e) {
                    dismissProgressDialog();
                }

                @Override
                public void onProgress(int progress) {
                }
            };
            UploadMethods.getInstance().photoUpload(subscriber, FileUtils.saveBitmap(bitmap, DeviceUtils.getUUID()));
            rxManager.add(subscriber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}