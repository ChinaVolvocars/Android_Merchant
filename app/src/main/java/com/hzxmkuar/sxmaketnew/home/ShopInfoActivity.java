package com.hzxmkuar.sxmaketnew.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.InfoBean;
import com.common.retrofit.entity.result.NewTestBean;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.entity.result.TestBean;
import com.common.retrofit.entity.resultImpl.CityBean;
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
import com.common.widget.editview.DeleteEditText;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.google.gson.Gson;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.activity.ChangeActivity;
import com.hzxmkuar.sxmaketnew.activity.UploadPicAndDescActivity;
import com.hzxmkuar.sxmaketnew.common.PictureCheckDialogFragment;
import com.hzxmkuar.sxmaketnew.common.photoPcker.MQPhotoPickerActivity;
import com.hzxmkuar.sxmaketnew.utils.BottomPickerUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的店铺 <br/>
 * Created by STH on 2018/5/16.
 */
public class ShopInfoActivity extends BaseMvpActivity {
    private static final String TAG = "ShopInfoActivity";
    /**
     * 修改界面的容器（修改）
     */
    private LinearLayout mllContentModify;
    /**
     * 店铺名称（修改）
     */
    private DeleteEditText mEdtStoreNameModify;
    /**
     * 选择店铺类型（修改）
     */
    private LinearLayout mLlStoreTypeModify;
    /**
     * 选择店铺类型（修改）
     */
    private TextView mTvStoreTypeModify;
    /**
     * 选择店铺所在城市（修改）
     */
    private LinearLayout mLlChoseStoreCityModify;
    /**
     * 选择店铺所在城市（修改）
     */
    private TextView mTvChoseStoreCityModify;
    /**
     * 店铺详细地址（修改）
     */
    private DeleteEditText mEdtStoreAddressDetailModify;
    /**
     * 商家营业电话
     */
    private DeleteEditText edt_shop_telephone;
    /**
     * 店铺描述（修改）
     */
    private DeleteEditText mEdtStoreDescModify;
    /**
     * 店头照片及数量（修改）
     */
    private TextView mTvStoreBgModify;
    /**
     * 店头照片图片（修改）
     */
    private ImageView mIvStoreCardBgModify;
    /**
     * 店家照片及数量（修改）
     */
    private TextView mTvStoreBgImgsModify;
    /**
     * 店家照片修改/添加（修改）
     */
    private Button mTvStoreBgAddModify;
    //    private Button btn_store_bg_reset_show;
    private Button mBtnStoreBgResetModify;
    //    private Button tv_store_bg_add_show;
    private ImageView mBack;
    private Button mBtnCommitInfo;
    private String path = "";
    private PictureCheckDialogFragment dialogFragment;
    private OptionsPickerView mStoreTypePicker;
    private String sheng = "";
    private String shi = "";
    private String qu = "";
    private String mProvinceId = "";
    private String mCityId = "";
    private String mCountryId = "";
    private String mDetailAdddes = "";
    private String shopName = "";
    /**
     * 商铺类型
     */
    private String type = "";
    private String shopTypeId = "";
    private String mShopDesc = "";
    private String pic = "";
    private String mPicList;
    private int mShop_pic_num;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private String mLat;
    private String mLng;
    /**
     * 可上传图片的空格位数
     */
    private int mCanUpdateImgs = 0;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_store_info_edit;
    }

    @Override
    protected void onViewCreated() {
        mllContentModify = (LinearLayout) findViewById(R.id.ll_content_modify);
        mEdtStoreNameModify = (DeleteEditText) findViewById(R.id.edt_store_name_modify);
        mLlStoreTypeModify = (LinearLayout) findViewById(R.id.ll_store_type_modify);
        mTvStoreTypeModify = (TextView) findViewById(R.id.tv_stroe_type_modify);
        mLlChoseStoreCityModify = (LinearLayout) findViewById(R.id.ll_chose_store_city_modify);
        mTvChoseStoreCityModify = (TextView) findViewById(R.id.tv_chose_store_city_modify);
        mEdtStoreAddressDetailModify = (DeleteEditText) findViewById(R.id.edt_stroe_address_detail_modify);
        edt_shop_telephone = (DeleteEditText) findViewById(R.id.edt_shop_telephone);
        mEdtStoreDescModify = (DeleteEditText) findViewById(R.id.edt_stroe_desc_modify);
        mTvStoreBgModify = (TextView) findViewById(R.id.tv_store_bg_modify);
        mIvStoreCardBgModify = (ImageView) findViewById(R.id.iv_stroe_card_bg_modify);
        mTvStoreBgImgsModify = (TextView) findViewById(R.id.tv_store_bg_imgs_modify);
        mTvStoreBgAddModify = (Button) findViewById(R.id.tv_store_bg_add_modify);
        mBtnStoreBgResetModify = (Button) findViewById(R.id.btn_store_bg_reset_modify);

//        btn_store_bg_reset_show = (Button) findViewById(R.id.btn_store_bg_reset_show);
//        tv_store_bg_add_show = (Button) findViewById(R.id.tv_store_bg_add_show);

        mBtnCommitInfo = (Button) findViewById(R.id.btn_commit_info);
        mBack = (ImageView) findViewById(R.id.back);
        init();
        switchViewIsNeedModify(true);
        statusLoading();
        getStoreInfoInit();
    }

    /**
     * 初始化拍照或选取照片的工具类
     */
    private void init() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
                uploadFile(outputFile.getPath());

            }
        }, false);//true裁剪，false不裁剪
    }

    /**
     * 获取店铺信息并初始化
     */
    private void getStoreInfoInit() {
        CommonSubscriber<InfoBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object obj) {
                statusContent();
                initStoreInfo(obj);
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        List<String> reqPara = new ArrayList<>();
        reqPara.add("time");
        reqPara.add("hashid");
        reqPara.add("uid");
        BusinessUserMethods.getInstance().getShopInfo(subscriber, reqPara);
        rxManager.add(subscriber);
    }


    @Override
    protected void doLogicFunc() {
        attachClickListener(mBack);
        attachClickListener(mBtnCommitInfo);
        attachClickListener(mIvStoreCardBgModify);
        attachClickListener(mTvStoreTypeModify);
        attachClickListener(mTvChoseStoreCityModify);
        attachClickListener(mTvStoreBgAddModify);
        attachClickListener(mBtnStoreBgResetModify);
        attachClickListener(mLlStoreTypeModify);
        attachClickListener(mLlChoseStoreCityModify);
        getStoreTypeList();
    }

    /**
     * 获取店铺类型分类列表
     */
    private void getStoreTypeList() {
        CommonSubscriber<NewTestBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                NewTestBean newTestBean = (NewTestBean) o;
                List<TestBean> data = newTestBean.getData();
                List<List<TestBean>> datas = new ArrayList<>();
                List<String> name = new ArrayList<>();
                List<List<String>> names = new ArrayList<>();

                for (TestBean testBean : data) {
                    datas.add(testBean.getList());
                    name.add(testBean.getName());
                    List<String> namea = new ArrayList<>();
                    for (TestBean b : testBean.getList()) {
                        String name1 = b.getName();
                        namea.add(name1);
                    }
                    names.add(namea);
                }
                initStoreTypePicker(datas, name, names);
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().scategory(subscriber);
        rxManager.add(subscriber);
    }


    /**
     * 获取店铺类型选择器
     *
     * @param datas 数据源
     * @param name  主分类
     * @param names 二级分类
     */
    private void initStoreTypePicker(final List<List<TestBean>> datas, List<String> name, List<List<String>> names) {
        mStoreTypePicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvStoreTypeModify.setText(datas.get(options1).get(options2).getName());
                shopTypeId = datas.get(options1).get(options2).getId();
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        mStoreTypePicker.setPicker(name, names);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mBack.getId()) {
            finish();
        } else if (view.getId() == mIvStoreCardBgModify.getId()) {
            showPicCheck();
        } else if (view.getId() == mTvStoreTypeModify.getId()) {
            mStoreTypePicker.show();
        } else if (view.getId() == mTvChoseStoreCityModify.getId() || view.getId() == mLlChoseStoreCityModify.getId()) {
            BottomPickerUtils.showCityPicker(context, new BottomPickerUtils.CityOptionPickerCallback() {
                @Override
                public void onOptionSelect(CityBean option1, CityBean option2, CityBean option3) {
                    sheng = option1.getArea();
                    shi = option2.getArea();
                    qu = option3.getArea();
                    mProvinceId = option1.getId() + "";
                    mCityId = option2.getId() + "";
                    mCountryId = option3.getId() + "";
                    mLat = option3.getLat();
                    mLng = option3.getLng();
                    mTvChoseStoreCityModify.setText(option1.getArea() + "" + option2.getArea() + "" + option3.getArea());
                }

            });
        } else if (view.getId() == mLlStoreTypeModify.getId()) {
            mStoreTypePicker.show();
        }
//        else if (view.getId() == mLlChoseStoreCityModify.getId()) {
//            BottomPickerUtils.showCityPicker(context, new BottomPickerUtils.CityOptionPickerCallback() {
//                @Override
//                public void onOptionSelect(CityBean option1, CityBean option2, CityBean option3) {
//                    sheng = option1.getArea();
//                    shi = option2.getArea();
//                    qu = option3.getArea();
//                    mProvinceId = option1.getId() + "";
//                    mCityId = option2.getId() + "";
//                    mCountryId = option3.getId() + "";
//                    mLat = option3.getLat();
//                    mLng = option3.getLng();
//                    mTvChoseStoreCityModify.setText(option1.getArea() + "" + option2.getArea() + "" + option3.getArea());
//                }
//
//            });
//        }
        else if (view.getId() == mBtnCommitInfo.getId()) {
            shopName = getEditTextStr(mEdtStoreNameModify);
            mDetailAdddes = getEditTextStr(mEdtStoreAddressDetailModify);
            mShopDesc = getEditTextStr(mEdtStoreDescModify);
            String shopMobile = getEditTextStr(edt_shop_telephone);
            if (shopName.equals("") || EmptyUtils.isEmpty(shopMobile) || shopTypeId.equals("") || mProvinceId.equals("") || mCityId.equals("") || mCountryId.equals("") || mDetailAdddes.equals("") ||
                    mShopDesc.equals("") || pic.equals("") || mPicList.equals("")) {
                showToastMsg("请填写完整的信息");
                return;
            }
            commitEditedInfo(shopName, shopTypeId, mProvinceId, mCityId, mCountryId, mDetailAdddes, mShopDesc, pic, shopMobile);
        } else if (view.getId() == mTvStoreBgAddModify.getId()) {
            startActivity(new Intent(context, ChangeActivity.class));
        } else if (view.getId() == mBtnStoreBgResetModify.getId()) {
            startActivityForResult(new Intent(context, UploadPicAndDescActivity.class).putExtra("num", mCanUpdateImgs).putExtra("name", mPicList), 100);
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
                uploadFile(path);
            }
        } else if (resultCode == 10) {
            mPicList = data.getStringExtra("name");
            mTvStoreBgImgsModify.setText("店家照片（" + mShop_pic_num + "/" + mCanUpdateImgs + "）");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadFile(final String imageUrl) {
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
                public void onUpLoadSuccess(PicBean s) {
                    dismissProgressDialog();
                    pic = s.getData();
                    ImageLoaderUtils.displaySmallPhoto(mIvStoreCardBgModify, pic);
                    mTvStoreBgModify.setText("店头照片（1/1）");
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

    /**
     * 根据需要切换视图，是否要将当前界面切换为修改界面
     *
     * @param isModify true 为修改界面，false 为展示界面
     */
    private void switchViewIsNeedModify(boolean isModify) {

        mllContentModify.setVisibility(View.VISIBLE);
        mBtnCommitInfo.setVisibility(View.VISIBLE);

    }

    /**
     * 初始化店铺信息
     *
     * @param obj 店铺信息对象
     */
    private void initStoreInfo(Object obj) {
        if (obj != null) {
            InfoBean bean = (InfoBean) obj;
            sheng = bean.getProvince_name();
            shi = bean.getArea_name();
            qu = bean.getCounty_name();
            mProvinceId = bean.getProvince();
            mCityId = bean.getArea();
            mCountryId = bean.getCounty();
            shopName = bean.getShop_name();
            type = bean.getCategory_name();
            mLat = bean.getLatitude();
            mLng = bean.getLongitude();
            shopTypeId = bean.getCategory_id();
            mDetailAdddes = bean.getAddress();
            mShopDesc = bean.getDesc();
            pic = bean.getFace();
            mShop_pic_num = bean.getShop_pic_num();
            edt_shop_telephone.setText(bean.getMobile());
            mTvChoseStoreCityModify.setText(bean.getProvince_name() + "" + bean.getArea_name() + "" + bean.getCounty_name());

            mEdtStoreNameModify.setText(shopName);
            mTvStoreTypeModify.setText(type);
            mEdtStoreAddressDetailModify.setText(mDetailAdddes);
            mEdtStoreDescModify.setText(mShopDesc);
            if (!pic.equals("")) {
                pic = pic.replace("\\", "/");
                ImageLoaderUtils.displaySmallPhoto(mIvStoreCardBgModify, pic);
                mTvStoreBgModify.setText("店头照片（1/1）");

            }
            List<InfoBean.ShopPicBean> beanList = bean.getShop_pic();
            if (beanList.size() > 0) {
                mCanUpdateImgs = beanList.size();
            }
            if (beanList.size() == 0) {
                mTvStoreBgImgsModify.setText("店家照片（0/" + mShop_pic_num + "）");
            } else {
                mTvStoreBgImgsModify.setText("店家照片（" + mShop_pic_num + "/" + mCanUpdateImgs + "）");
            }
            mPicList = new Gson().toJson(beanList);
        }
    }

    /**
     * 提交编辑后的资料  <br/>
     * time         :  时间（必填）
     * hash         :  加密值  34ca17207989aa48a6d76f259db5ee4e（必填）  <br/>
     * hashid       :  用户加密值（必填）  <br/>
     * uid          :  用户id（必填）  <br/>
     * shop_name    :  商家名称（必填）  <br/>
     * category_id  :  商家类型id（必填）  <br/>
     * province     :  省id（必填）  <br/>
     * area         :  市id（必填）  <br/>
     * county       :  区id（必填）  <br/>
     * address      :  商家详细地址（必填）  <br/>
     * desc         :  商家简介（必填）  <br/>
     * face         :  商家店头照url（必填）  <br/>
     */
    private void commitEditedInfo(String shop_name, String shop_type, String shengid, String shiid, String quid,
                                  String adddes, String shop_desc, String pic, String mobile) {
        showProgressingDialog();
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                switchViewIsNeedModify(true);
                getStoreInfoInit();
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
        commitInfoList.add("shop_name");
        commitInfoList.add("category_id");
        commitInfoList.add("province");
        commitInfoList.add("area");
        commitInfoList.add("county");
        commitInfoList.add("address");
        commitInfoList.add("desc");
        commitInfoList.add("face");
        commitInfoList.add("mobile");
        BusinessUserMethods.getInstance().shopInfoEditedCommitt(subscriber, shop_name, shop_type, shengid, shiid, quid, adddes, shop_desc, pic,
                commitInfoList, mobile);
        rxManager.add(subscriber);
    }
}
