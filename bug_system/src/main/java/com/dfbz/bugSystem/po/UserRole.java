package com.dfbz.bugSystem.po;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class UserRole implements Serializable {
    private Integer userId;

    private Integer roleId;

    private String registerId;

    private String registerDate;

    private String updateId;

    private String updateDate;

    private Integer delFlag;

    private String delDate;

    public UserRole(Integer userId, Integer roleId, String registerId, String registerDate, Integer delFlag) {
        this.userId = userId;
        this.roleId = roleId;
        this.registerId = registerId;
        this.registerDate = registerDate;
        this.delFlag = delFlag;
    }

    public UserRole() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId == null ? null : registerId.trim();
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate == null ? null : registerDate.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate == null ? null : delDate.trim();
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}