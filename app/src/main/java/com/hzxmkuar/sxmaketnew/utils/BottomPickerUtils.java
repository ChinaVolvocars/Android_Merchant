package com.hzxmkuar.sxmaketnew.utils;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.resultImpl.CityBean;
import com.common.retrofit.entity.resultImpl.CityLocalBean;
import com.common.utils.EmptyUtils;
import com.common.utils.StringUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by leo on 2017/7/5.
 * 滑动选择工具类
 */
public class BottomPickerUtils {
    private static OptionsPickerView optionsPickerView;
    protected static CityLocalBean cityLocalBean;

    /**
     * 一级滑动选择控件
     *
     * @param context  ctx
     * @param data     数据源
     * @param callback 点击回调
     */
    public static <T> void showOptionPicker(Context context, List<T> data, final OptionPickerCallback callback) {
        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (callback != null) {
                    callback.onOptionSelect(options1);
                }
            }
        });
        builder.isDialog(false);
        builder.setBgColor(0xFFFFFFFF);
        builder.setSubmitColor(0xFFFB2C3C);
        builder.setCancelColor(0xFFFB2C3C);
        builder.setContentTextSize(15);
        builder.setLineSpacingMultiplier(3.5f);
        builder.setOutSideCancelable(true);
        OptionsPickerView optionsPickerView = new OptionsPickerView<>(builder);
        optionsPickerView.setPicker(data);
        optionsPickerView.show();
    }

    /**
     * 多级不联动滑动选择控件
     *
     * @param context  ctx
     * @param data     一级数据源
     * @param dataK    二级数据源
     * @param dataL    三级数据源
     * @param callback 点击回调
     */
    public static <T, K, L> void showMutilOptionPicker(Context context, List<T> data, List<K> dataK, List<L> dataL, final MutilOptionPickerCallback callback) {
        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (callback != null) {
                    callback.onOptionSelect(options1, options2, options3);
                }
            }
        });
        builder.isDialog(false);
        builder.setBgColor(0xFFFFFFFF);
        builder.setSubmitColor(0xFFFB2C3C);
        builder.setCancelColor(0xFFFB2C3C);
        builder.setContentTextSize(15);
        builder.setLineSpacingMultiplier(3.5f);
        builder.setOutSideCancelable(true);
        OptionsPickerView optionsPickerView = new OptionsPickerView<>(builder);
        optionsPickerView.setNPicker(data, dataK, dataL);
        optionsPickerView.show();
    }

    /**
     * 多级联动滑动选择控件
     *
     * @param context  ctx
     * @param data     一级数据源
     * @param dataK    二级数据源
     * @param dataL    三级数据源
     * @param callback 点击回调
     */
    public static <T, K, L> void showOptionPicker(Context context, List<T> data, List<K> dataK, List<L> dataL, final OptionPickerCallback callback) {
        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (callback != null) {
                    callback.onOptionSelect(options1);
                }
            }
        });
        builder.isDialog(false);
        builder.setBgColor(0xFFFFFFFF);
        builder.setSubmitColor(0xFFFB2C3C);
        builder.setCancelColor(0xFFFB2C3C);
        builder.setContentTextSize(15);
        builder.setLineSpacingMultiplier(3.5f);
        builder.setOutSideCancelable(true);
        OptionsPickerView optionsPickerView = new OptionsPickerView<>(builder);
        optionsPickerView.setPicker(data, dataK, dataL);
        optionsPickerView.show();
    }

    /**
     * 日期三级联动的对话框
     *
     * @param context    ctx
     * @param startDate  开始时间   Calendar.getInstance().setData()
     * @param endDate    结束时间   Calendar.getInstance()
     * @param selectDate 默认选中时间
     * @param callback   选中回调
     */
    public static void showDatePicker(Context context, Calendar startDate, Calendar endDate, Calendar selectDate, final DatePickerCallback callback) {
        TimePickerView.Builder builder = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (callback != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    callback.onDateSelect(calendar);
                }
            }
        });
        builder.setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN);
        builder.setLabel("年", "月", "日", "时", "分", "秒");
        builder.isCenterLabel(false);
        builder.isDialog(false);
        builder.setBgColor(0xFFFFFFFF);
        builder.setSubmitColor(0xFFFB2C3C);
        builder.setCancelColor(0xFFFB2C3C);
        builder.setContentSize(15);
        builder.setLineSpacingMultiplier(3.5f);
        builder.setRangDate(startDate, endDate);
        TimePickerView pickerView = builder.build();
        pickerView.setDate(selectDate);
        pickerView.show();
    }

    /*--------------------------------------------城市选择三级联动-------------------------------------------*/
    private static List<CityBean> options1Items = new ArrayList<>();
    private static List<List<CityBean>> options2Items = new ArrayList<>();
    private static List<List<List<CityBean>>> options3Items = new ArrayList<>();
    private static List<String> options1Itemss = new ArrayList<>();
    private static List<List<String>> options2Itemss = new ArrayList<>();
    private static List<List<List<String>>> options3Itemss = new ArrayList<>();

    /**
     * 多级联动滑动选择控件
     *
     * @param context  ctx
     * @param callback 点击回调
     */
    public static void showCityPicker(final Context context, final CityOptionPickerCallback callback) {
        initData(context, callback);
    }

    /**
     * 多级联动滑动选择控件
     *
     * @param context  ctx
     * @param callback 点击回调
     */
    public static void initCityPicker(Context context, final CityOptionPickerCallback callback) {
        if (EmptyUtils.isEmpty(options1Items) || EmptyUtils.isEmpty(options2Items) || EmptyUtils.isEmpty(options3Items)) {
            return;
        }
        if (EmptyUtils.isEmpty(options1Itemss) || EmptyUtils.isEmpty(options2Itemss) || EmptyUtils.isEmpty(options3Itemss)) {
            return;
        }

        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (callback != null) {
                    callback.onOptionSelect(options1Items.get(options1), options2Items.get(options1).get(options2),
                            options3Items.get(options1).get(options2).get(options3));
                }
            }
        });
        builder.isDialog(false);
        builder.setBgColor(0xFFFFFFFF);
        builder.setSubmitColor(0xFFFB2C3C);
        builder.setCancelColor(0xFFFB2C3C);
        builder.setContentTextSize(15);
        builder.setLineSpacingMultiplier(3.5f);
        builder.setOutSideCancelable(true);
        optionsPickerView = new OptionsPickerView<>(builder);
        optionsPickerView.setPicker(options1Itemss, options2Itemss, options3Itemss);
        optionsPickerView.show();
    }

    private static List<CityBean> setProvinceList() {
        List<CityBean> lists = new ArrayList<>();

        for (int i = 0; i < cityLocalBean.getRECORD().size(); i++) {
            if (EmptyUtils.isNotEmpty(cityLocalBean.getRECORD().get(i).getArea())) {
                lists.add(cityLocalBean.getRECORD().get(i));
            }
        }

        return lists;
    }

    private static List<String> setProvinceLists() {
        List<String> lists = new ArrayList<>();

        for (int i = 0; i < cityLocalBean.getRECORD().size(); i++) {
            if (EmptyUtils.isNotEmpty(cityLocalBean.getRECORD().get(i).getArea())) {
                lists.add(cityLocalBean.getRECORD().get(i).getArea());
            }
        }

        return lists;
    }

    private static List<List<CityBean>> setCityList() {
        List<List<CityBean>> lists = new ArrayList<>();

        for (int i = 0; i < cityLocalBean.getRECORD().size(); i++) {
            List<CityBean> listss = new ArrayList<>();
            if (1 == cityLocalBean.getRECORD().get(i).getLevel() && EmptyUtils.isNotEmpty(cityLocalBean.getRECORD().get(i).getSub())) {
                for (int j = 0; j < cityLocalBean.getRECORD().get(i).getSub().size(); j++) {
                    listss.add(cityLocalBean.getRECORD().get(i).getSub().get(j));
                }
            }
            lists.add(listss);
        }

        return lists;
    }

    private static List<List<String>> setCityLists() {
        List<List<String>> lists = new ArrayList<>();

        for (int i = 0; i < cityLocalBean.getRECORD().size(); i++) {
            List<String> listss = new ArrayList<>();
            if (1 == cityLocalBean.getRECORD().get(i).getLevel() && EmptyUtils.isNotEmpty(cityLocalBean.getRECORD().get(i).getSub())) {
                for (int j = 0; j < cityLocalBean.getRECORD().get(i).getSub().size(); j++) {
                    listss.add(cityLocalBean.getRECORD().get(i).getSub().get(j).getArea());
                }
            }
            lists.add(listss);
        }

        return lists;
    }

    private static List<List<List<CityBean>>> setAreaList() {
        List<List<List<CityBean>>> lists = new ArrayList<>();

        for (int i = 0; i < cityLocalBean.getRECORD().size(); i++) {

            List<List<CityBean>> listss = new ArrayList<>();

            if (EmptyUtils.isNotEmpty(cityLocalBean.getRECORD().get(i).getSub())) {

                for (int j = 0; j < cityLocalBean.getRECORD().get(i).getSub().size(); j++) {
                    List<CityBean> listsss = new ArrayList<>();
                    if (EmptyUtils.isNotEmpty(cityLocalBean.getRECORD().get(i).getSub().get(j).getSub())) {

                        for (int k = 0; k < cityLocalBean.getRECORD().get(i).getSub().get(j).getSub().size(); k++) {
                            listsss.add(cityLocalBean.getRECORD().get(i).getSub().get(j).getSub().get(k));
                        }
                        listss.add(listsss);
                    }
                }

                lists.add(listss);
            }

        }

        return lists;
    }

    private static List<List<List<String>>> setAreaLists() {
        List<List<List<String>>> lists = new ArrayList<>();

        for (int i = 0; i < cityLocalBean.getRECORD().size(); i++) {

            List<List<String>> listss = new ArrayList<>();

            if (EmptyUtils.isNotEmpty(cityLocalBean.getRECORD().get(i).getSub())) {

                for (int j = 0; j < cityLocalBean.getRECORD().get(i).getSub().size(); j++) {
                    List<String> listsss = new ArrayList<>();
                    if (EmptyUtils.isNotEmpty(cityLocalBean.getRECORD().get(i).getSub().get(j).getSub())) {

                        for (int k = 0; k < cityLocalBean.getRECORD().get(i).getSub().get(j).getSub().size(); k++) {
                            listsss.add(cityLocalBean.getRECORD().get(i).getSub().get(j).getSub().get(k).getArea());
                        }
                        listss.add(listsss);
                    }
                }

                lists.add(listss);
            }

        }

        return lists;
    }

    // 初始化城市列表数据
    private static void initData(final Context context, final CityOptionPickerCallback callback) {
        if (EmptyUtils.isNotEmpty(DataCenter.getInstance().getCityLocalBean())) {
            initCityPicker(context, callback);
            cityLocalBean = DataCenter.getInstance().getCityLocalBean();
            options1Items = setProvinceList();
            options2Items = setCityList();
            options3Items = setAreaList();
            options1Itemss = setProvinceLists();
            options2Itemss = setCityLists();
            options3Itemss = setAreaLists();
            return;
        }

        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(StringUtils.getLocalJson(context, "province.json"));
                subscriber.onCompleted();
            }
        }).map(new Func1<String, CityLocalBean>() {
            @Override
            public CityLocalBean call(String s) {
                cityLocalBean = new Gson().fromJson(s, CityLocalBean.class);
                options1Items = setProvinceList();
                options2Items = setCityList();
                options3Items = setAreaList();
                options1Itemss = setProvinceLists();
                options2Itemss = setCityLists();
                options3Itemss = setAreaLists();
                return cityLocalBean;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CityLocalBean>() {
                    @Override
                    public void call(CityLocalBean address) {
                        if (EmptyUtils.isNotEmpty(address)) {
                            DataCenter.getInstance().setCityLocalBean(address);
                            cityLocalBean = address;
                            initCityPicker(context, callback);
                        }
                    }
                });
    }

    public List<String> getOne() {
        return null;
    }

    /*--------------------------------------------事件回调-------------------------------------*/
    public interface OptionPickerCallback {
        void onOptionSelect(int index);
    }

    public interface CityOptionPickerCallback {
        void onOptionSelect(CityBean option1, CityBean option2, CityBean option3);
    }

    public interface MutilOptionPickerCallback {
        void onOptionSelect(int option1, int option2, int option3);
    }

    public interface DatePickerCallback {
        void onDateSelect(Calendar calendar);
    }
}