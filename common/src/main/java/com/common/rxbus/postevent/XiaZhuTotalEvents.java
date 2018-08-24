package com.common.rxbus.postevent;

import com.common.utils.StringUtils;

public class XiaZhuTotalEvents {

   private String total1;
    private String total2;
    private String total3;

    public XiaZhuTotalEvents(String total1, String total2, String total3) {
        this.total1 = total1;
        this.total2 = total2;
        this.total3 = total3;
    }

    public String getTotal1() {

        return StringUtils.nullToStr(total1);
    }

    public String getTotal2() {
        return StringUtils.nullToStr(total2);
    }

    public String getTotal3() {
        return StringUtils.nullToStr(total3);
    }

    public void setTotal1(String total1) {
        this.total1 = total1;
    }

    public void setTotal2(String total2) {
        this.total2 = total2;
    }

    public void setTotal3(String total3) {
        this.total3 = total3;
    }
}
