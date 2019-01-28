package com.common.retrofit.entity.result;

/**
 *   语音播报设备绑定实体类        <br/>
 * Created by jc on 2019/1/28.
 */
public class BindDeviceEntity {

    private String status;
    private String dev_num;
    private String dev_status;

    /**
     *  设备是否已经绑定        <br/>
     *  0 为 未绑定     <br/>
     *  1 为 已绑定     <br/>
     * @return
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDev_num() {
        return dev_num;
    }

    public void setDev_num(String dev_num) {
        this.dev_num = dev_num;
    }

    public String getDev_status() {
        return dev_status;
    }

    public void setDev_status(String dev_status) {
        this.dev_status = dev_status;
    }
}
