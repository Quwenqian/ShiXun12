package com.dfbz.bugSystem.po;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class User implements Serializable {

    private Role role;

    private UserRole userRole;

    private Integer userId;


    private String userName;


    private String password;


    private Integer projectId;


    private Integer teamId;


    private String registerId;


    private String registerDate;

    private String updateId;


    private String updateDate;


    private Integer delFlag;


    private String delDate;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public Integer getProjectId() {
        return projectId;
    }


    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }


    public Integer getTeamId() {
        return teamId;
    }


    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {

        return JSONObject.toJSONString(this);
    }
}