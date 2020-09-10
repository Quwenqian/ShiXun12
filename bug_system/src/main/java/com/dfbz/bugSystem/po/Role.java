package com.dfbz.bugSystem.po;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Role implements Serializable {

    private Integer roleId;

    private String roleName;


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


    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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
}