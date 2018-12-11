package com.cong.springx.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorRsp {


    @JsonProperty("error_code")
    private String errorCode;


    @JsonProperty("error_msg")
    private String errorMsg;

    public ErrorRsp(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorRsp() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
