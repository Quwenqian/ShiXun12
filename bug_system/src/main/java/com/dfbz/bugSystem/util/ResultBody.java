package com.dfbz.bugSystem.util;

import com.alibaba.fastjson.JSONObject;
import com.dfbz.bugSystem.exception.BaseErrorInfoInterface;

/**
 * 自定义controller层的数据返回格式
 */
public class ResultBody {

    //响应码
    private String code;
    //响应信息
    private String message;
    //响应数据
    private Object data;

    public ResultBody() {
    }


    /**
     * 已自定义异常接口为参数的构造函数
     */
    public ResultBody(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }


    /*getter and setter*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    /**
     * 成功方法
     * */

    public static ResultBody success(Object data){
        ResultBody rb = new ResultBody();
        rb.setCode(CommonEnum.SUCCESS.getCode());
        rb.setMessage(CommonEnum.SUCCESS.getMessage());
        rb.setData(data);
        return rb;
    }

    public static ResultBody success(){
        return success(null);
    }


    /**
     * 失败方法
     */
    public static ResultBody error(String message){
        ResultBody rb = new ResultBody();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    public static ResultBody error(String code,String message){
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }


    public static ResultBody error(CommonEnum ce){
        ResultBody rb = new ResultBody();
        rb.setCode(ce.getCode());
        rb.setMessage(ce.getMessage());
        rb.setData(null);
        return rb;
    }




    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
