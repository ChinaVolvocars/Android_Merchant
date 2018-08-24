package com.hzxmkuar.sxmaketnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.AddPicBean;
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
public class MydpActivity extends BaseMvpActivity {
    /**
     * 修改
     */
    private TextView mTvModify;
    /**
     * 正常展示的界面的容器(展示)
     */
    private LinearLayout mLlNormalContentShow;
    /**
     * 店铺名称（展示）
     */
    private TextView mTvStoreNamesShow;
    /**
     * 店铺描述（展示）
     */
    private TextView mTvStoreDescShow;
    /**
     * 店铺类型（展示）
     */
    private TextView mTvStoreTypeShow;
    /**
     * 店铺所在城市（展示）
     */
    private TextView mTvCityShow;
    /**
     * 店家描述照片及数量（展示）
     */
    private TextView mTvStoreDescImgShow;
    /**
     * 修改/添加店家照片（展示）
     */
//    private TextView mTvModifyOrAddImgShow;
//    mTvModifyOrAddImgShow = (TextView) findViewById(R.id.tv_modify_or_add_img_show);
    /**
     * 店头照片及数量（展示）
     */
    private TextView mTvStoreBgShow;
    /**
     * 店头照片图片（展示）
     */
    private ImageView mIvStoreCardBgShow;
    /**
     * 店铺详细地址（展示）
     */
    private TextView mAddressesDetailShow;
    /* ******************************************************************** 修改部分 ***************************************************************/
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
    private TextView mTvStoreBgAddModify;

    private ImageView mBack;

    private Button mNext;
    private String path = "";
    private PictureCheckDialogFragment dialogFragment;
    private OptionsPickerView mStoreTypePicker;
    private boolean isBj = false;

    private String sheng = "";
    private String shi = "";
    private String qu = "";
    private String shengid = "";
    private String shiid = "";
    private String quid = "";
    private String name = "";
    /**
     * 商铺类型
     */
    private String type = "";
    private String typeid = "";
    private String showdes = "";
    private String adddes = "";
    private String pic = "";
    private String mPicList;
    private int mShop_pic_num;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private String mLat;
    private String mLng;

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
        mTvModify = (TextView) findViewById(R.id.tv_modify);
/* ******************************************************************** 展示部分 ***************************************************************/
        mLlNormalContentShow = (LinearLayout) findViewById(R.id.ll_normal_content_show);
        mTvStoreNamesShow = (TextView) findViewById(R.id.tv_store_name_show);
        mTvStoreDescShow = (TextView) findViewById(R.id.tv_store_desc_show);
        mTvStoreTypeShow = (TextView) findViewById(R.id.tv_store_type_show);
        mTvCityShow = (TextView) findViewById(R.id.tv_city_show);
        mTvStoreDescImgShow = (TextView) findViewById(R.id.tv_stroe_desc_img_show);
        mTvStoreBgShow = (TextView) findViewById(R.id.tv_store_bg_show);
        mIvStoreCardBgShow = (ImageView) findViewById(R.id.iv_store_card_bg_show);
        mAddressesDetailShow = (TextView) findViewById(R.id.addressss_detils_show);
/* ******************************************************************** 修改部分 ***************************************************************/
        mllContentModify = (LinearLayout) findViewById(R.id.ll_content_modify);
        mEdtStoreNameModify = (DeleteEditText) findViewById(R.id.edt_store_name_modify);
        mLlStoreTypeModify = (LinearLayout) findViewById(R.id.ll_store_type_modify);
        mTvStoreTypeModify = (TextView) findViewById(R.id.tv_stroe_type_modify);
        mLlChoseStoreCityModify = (LinearLayout) findViewById(R.id.ll_chose_store_city_modify);
        mTvChoseStoreCityModify = (TextView) findViewById(R.id.tv_chose_store_city_modify);
        mEdtStoreAddressDetailModify = (DeleteEditText) findViewById(R.id.edt_stroe_address_detail_modify);
        mEdtStoreDescModify = (DeleteEditText) findViewById(R.id.edt_stroe_desc_modify);
        mTvStoreBgModify = (TextView) findViewById(R.id.tv_store_bg_modify);
        mIvStoreCardBgModify = (ImageView) findViewById(R.id.iv_stroe_card_bg_modify);
        mTvStoreBgImgsModify = (TextView) findViewById(R.id.tv_store_bg_imgs_modify);
        mTvStoreBgAddModify = (TextView) findViewById(R.id.tv_store_bg_add_modify);

        mNext = (Button) findViewById(R.id.next);
        mBack = (ImageView) findViewById(R.id.back);
        init();
        switchViewIsNeedModify(false);
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
        BusinessUserMethods.getInstance().shopInfo(subscriber);
        rxManager.add(subscriber);
    }


    @Override
    protected void doLogicFunc() {
        attachClickListener(mBack);
        attachClickListener(mNext);
        attachClickListener(mIvStoreCardBgModify);
        attachClickListener(mTvStoreTypeModify);
        attachClickListener(mTvChoseStoreCityModify);
        attachClickListener(mTvStoreBgAddModify);
        attachClickListener(mLlStoreTypeModify);
        attachClickListener(mLlChoseStoreCityModify);
        attachClickListener(mTvModify);
        attachClickListener(mTvStoreBgImgsModify);
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
                typeid = datas.get(options1).get(options2).getId();
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
        } else if (view.getId() == mTvChoseStoreCityModify.getId()) {
            BottomPickerUtils.showCityPicker(context, new BottomPickerUtils.CityOptionPickerCallback() {
                @Override
                public void onOptionSelect(CityBean option1, CityBean option2, CityBean option3) {
                    sheng = option1.getArea();
                    shi = option2.getArea();
                    qu = option3.getArea();
                    shengid = option1.getId() + "";
                    shiid = option2.getId() + "";
                    quid = option3.getId() + "";
                    mLat = option3.getLat();
                    mLng = option3.getLng();
                    mTvChoseStoreCityModify.setText(option1.getArea() + "" + option2.getArea() + "" + option3.getArea());
                }

            });
        } else if (view.getId() == mLlStoreTypeModify.getId()) {
            mStoreTypePicker.show();
        } else if (view.getId() == mLlChoseStoreCityModify.getId()) {
            BottomPickerUtils.showCityPicker(context, new BottomPickerUtils.CityOptionPickerCallback() {
                @Override
                public void onOptionSelect(CityBean option1, CityBean option2, CityBean option3) {
                    sheng = option1.getArea();
                    shi = option2.getArea();
                    qu = option3.getArea();
                    shengid = option1.getId() + "";
                    shiid = option2.getId() + "";
                    quid = option3.getId() + "";
                    mLat = option3.getLat();
                    mLng = option3.getLng();
                    mTvChoseStoreCityModify.setText(option1.getArea() + "" + option2.getArea() + "" + option3.getArea());
                }

            });
        } else if (view.getId() == mNext.getId()) {
            name = getEditTextStr(mEdtStoreNameModify);
            adddes = getEditTextStr(mEdtStoreAddressDetailModify);
            showdes = getEditTextStr(mEdtStoreDescModify);
            if (name.equals("") || typeid.equals("") || shengid.equals("") || shiid.equals("") || quid.equals("") || adddes.equals("") ||
                    showdes.equals("") || pic.equals("") || mPicList.equals("")) {
                showToastMsg("请填写完整的信息");
                return;
            }
            goToHttpReqsss(name, typeid, shengid, shiid, quid, adddes, showdes, pic, mPicList, mLat, mLng);
        } else if (view.getId() == mTvModify.getId()) {
            switchViewIsNeedModify(true);
        } else if (view.getId() == mTvStoreBgAddModify.getId()) {
            startActivity(new Intent(context, ChangeActivity.class));
        } else if (view.getId() == mTvStoreBgImgsModify.getId()) {
            startActivityForResult(new Intent(context, PicSetActivity.class).putExtra("num", mShop_pic_num).putExtra("name", mPicList), 100);
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
            mTvStoreBgImgsModify.setText("店家照片（" + mShop_pic_num + "/" + mShop_pic_num + "）");
            mTvStoreDescImgShow.setText("店家照片（" + mShop_pic_num + "/" + mShop_pic_num + "）");
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
                    mTvStoreBgShow.setText("店头照片（1/1）");
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
        if (isModify) {
            mllContentModify.setVisibility(View.VISIBLE);
            mLlNormalContentShow.setVisibility(View.GONE);
            mTvModify.setVisibility(View.GONE);
            mNext.setVisibility(View.VISIBLE);
            isBj = true;
        } else {
            mllContentModify.setVisibility(View.GONE);
            mLlNormalContentShow.setVisibility(View.VISIBLE);
            mTvModify.setVisibility(View.VISIBLE);
            mNext.setVisibility(View.GONE);
            isBj = false;
        }
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
            shengid = bean.getProvince();
            shiid = bean.getArea();
            quid = bean.getCounty();
            name = bean.getShop_name();
            type = bean.getCategory_name();
            mLat = bean.getLatitude();
            mLng = bean.getLongitude();
            typeid = bean.getCategory_id();
            adddes = bean.getAddress();
            showdes = bean.getDesc();
            pic = bean.getFace();
            mShop_pic_num = bean.getShop_pic_num();

            mTvStoreNamesShow.setText(name);
            mEdtStoreNameModify.setText(name);
            mTvStoreTypeShow.setText(type);
            mTvStoreTypeModify.setText(type);
            mTvChoseStoreCityModify.setText(sheng + "" + shi + "" + qu);
            mTvCityShow.setText(sheng + "" + shi + "" + qu);
            mAddressesDetailShow.setText(adddes);
            mEdtStoreAddressDetailModify.setText(adddes);
            mEdtStoreDescModify.setText(showdes);
            mTvStoreDescShow.setText(showdes);
            if (!pic.equals("")) {
                pic = pic.replace("\\", "/");
                ImageLoaderUtils.displaySmallPhoto(mIvStoreCardBgModify, pic);
                ImageLoaderUtils.displaySmallPhoto(mIvStoreCardBgShow, pic);
                mTvStoreBgModify.setText("店头照片（1/1）");
                mTvStoreBgShow.setText("店头照片（1/1）");
            }
            List<InfoBean.ShopPicBean> beanList = bean.getShop_pic();
            List<AddPicBean> beanLists = new ArrayList<>();
            if (beanList.size() > 0) {
                for (InfoBean.ShopPicBean bean1 : beanList) {
                    beanLists.add(new AddPicBean(bean1.getShop_pic(), bean1.getShop_description()));
                }
            }
            if (beanList.size() == 0) {
                mTvStoreBgImgsModify.setText("店家照片（0/" + mShop_pic_num + "）");
                mTvStoreDescImgShow.setText("店家照片（0/" + mShop_pic_num + "）");
            } else {
                mTvStoreBgImgsModify.setText("店家照片（" + mShop_pic_num + "/" + mShop_pic_num + "）");
                mTvStoreDescImgShow.setText("店家照片（" + mShop_pic_num + "/" + mShop_pic_num + "）");
            }
            mPicList = new Gson().toJson(beanLists);
        }
    }

    private void goToHttpReqsss(String name, String typeid, String shengid, String shiid, String quid,
                                String adddes, String showdes, String pic, String picList, String pics, String picLists) {
        showProgressingDialog();
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                switchViewIsNeedModify(false);
                getStoreInfoInit();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });

        BusinessUserMethods.getInstance().shopInfoSubmit(subscriber, name, typeid, shengid, shiid, quid, adddes, showdes, pic, picList, pics, picLists);
        rxManager.add(subscriber);
    }
}
