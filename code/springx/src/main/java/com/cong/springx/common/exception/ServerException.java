package com.cong.springx.common.exception;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * 服务异常类
 */
public class ServerException extends RuntimeException {

    private static final Gson GSON = new Gson();

    @SerializedName("error_code")
    private String code;

    @SerializedName("error_msg")
    private String msg;
    /**
     * http状态吗
     */
    private int status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ServerException() {
    }

    /**
     * 指定状态码
     * @param code
     */
    public ServerException(String code) {
        this.code = code;
    }

    /**
     * 指定状态码 和 消息
     * @param code
     * @param msg
     */
    public ServerException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 指定错误码 错误信息 和 http状态
     * @param code
     * @param msg
     * @param status
     */
    public ServerException(String code, String msg, int status) {
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }
}
