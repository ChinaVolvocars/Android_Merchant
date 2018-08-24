package com.common.retrofit.entity.resultImpl;

import com.common.base.Constants;
import com.common.utils.StringUtils;

public class HttpStatus {

    private int Code;
    private String Msg;

    /**
     * API是否请求失败
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return !(Code == Constants.SERVER_RESP_SUCCESS_CODE);
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        this.Msg = msg;
    }
}