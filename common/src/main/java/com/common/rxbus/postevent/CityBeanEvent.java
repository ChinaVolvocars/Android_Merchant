package com.common.rxbus.postevent;

import com.common.retrofit.entity.resultImpl.CityEntity;

public class CityBeanEvent
{
    private CityEntity bean;

    public CityBeanEvent(CityEntity bean) {
        this.bean = bean;
    }

    public CityEntity getCityBean() {
        return bean;
    }
}
