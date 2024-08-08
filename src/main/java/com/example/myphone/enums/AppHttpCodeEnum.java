package com.example.myphone.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    ADD_ERROR(422,"添加失败"),
    SYSTEM_ERROR(500, "出现错误"),
    UPDATE_ERROR(423,"添加新工法失败"),
    MILEAGE_ERROR(424,"起点里程或终点里程格式有误");


    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}