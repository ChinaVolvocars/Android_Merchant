package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/4/16.
 */

public class TimeBeans {

    /**
     * time : {"9":"9\u65f6","10":"10\u65f6","13":"13\u65f6","14":"14\u65f6","15":"15\u65f6","16":"16\u65f6","17":"17\u65f6","18":"18\u65f6"}
     */

    private String time;

    public TimeBeans(String time, String time_des) {

        this.time = time;
        this.time_des = time_des;
    }

    public String getTime_des() {
        return time_des;
    }

    public void setTime_des(String time_des) {
        this.time_des = time_des;
    }

    private String time_des;

}
