package com.dfbz.bugSystem.po;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class UserModule implements Serializable {
    private Integer roleId;

    private Integer moduleId;

    private Integer userId;

    private String registerId;

    private String registerDate;

    private String updateId;

    private String updateDate;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public UserModule() {
    }

    public UserModule(Integer roleId, Integer moduleId, Integer userId, String registerId, String registerDate) {
        this.roleId = roleId;
        this.moduleId = moduleId;
        this.userId = userId;
        this.registerId = registerId;
        this.registerDate = registerDate;
    }
}