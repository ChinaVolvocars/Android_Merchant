package com.common.retrofit.entity.resultImpl;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/*******************************
* 本地全国地址转换实体类
* @author syc
* created at 2017/4/20 上午 11:47
********************************/
public class CityLocalBean implements IPickerViewData {

    /**
     * RECORD : [{"id":"110000","pid":"0","area":"北京","pinyin":"bei jing","first_code":"B","level":"1","status":"1","isdd":"0"},{"id":"110100","pid":"110000","area":"北京市","pinyin":"bei jing shi","first_code":"B","level":"2","status":"1","isdd":"1"}]
     */

    private List<CityBean> RECORD;

    public void setRECORD(List<CityBean> RECORD) {
        this.RECORD = RECORD;
    }

    public List<CityBean> getRECORD() {
        return RECORD;
    }

    @Override
    public String getPickerViewText() {
        return null;
    }
}
