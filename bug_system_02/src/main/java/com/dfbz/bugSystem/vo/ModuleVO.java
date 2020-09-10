package com.dfbz.bugSystem.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 用于在权限分配页面所显示的对象结构
 */
public class ModuleVO  implements Serializable {

    private Integer moduleId;
    private String moduleName;
    private Boolean flag;


    public ModuleVO() {
    }

    public ModuleVO(Integer moduleId, String moduleName, Boolean flag) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.flag = flag;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
