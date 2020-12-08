package com.zhuzhu.demo.configcenter.pojo;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class ConfMgmtResp implements Serializable {

    private static final long serialVersionUID = -1154019912144174917L;

    private int code;
    private JsonObject data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ConfMgmtResp{" +
                "code=" + code +
                ", data='" + data + '\'' +
                '}';
    }
}
