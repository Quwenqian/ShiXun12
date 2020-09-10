package com.dfbz.bugSystem.util;

import com.dfbz.bugSystem.exception.BaseErrorInfoInterface;

/**
 * 自定义枚举类
 */
public enum CommonEnum implements BaseErrorInfoInterface {
    SUCCESS("200","成功!"),
    NOT_FOUND("404","未找到该资源!"),
    INTERNAL_SERVER_ERROR("500","服务器内部错误!"),
    BODY_NOT_MATCH("400","请求数据格式不匹配!"),
    NOT_PERMISSION("403","访问权限受限!"),
    MEDIA_NOT_ALLOW("415","无法支持该媒体类型!"),
    SERVER_BUSY("503","服务器忙，请稍后再试!")
    ;


    //状态码
    private String code;
    //信息
    private String message;

    CommonEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
