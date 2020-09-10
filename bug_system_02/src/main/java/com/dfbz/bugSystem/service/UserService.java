package com.dfbz.bugSystem.service;

import com.dfbz.bugSystem.po.Module;
import com.dfbz.bugSystem.po.Role;
import com.dfbz.bugSystem.po.User;

import java.util.List;

public interface UserService {

    /**
     * 用户添加方法
     * @param user
     * @return
     */
    int addUser(User user);


    User login(User user);

    /**
     * 根据userid查找用户信息
     * @param id
     * @return
     */
    User findById(Integer id);


    /**
     * 获取当前用户的角色列表
     * @param userId
     * @return
     */
    List<Role> getUserRoles(Integer userId);

    /**
     * 获得当前用户具有的权限列表
     * @param userId
     * @return
     */
    List<Module> getUserModules(Integer userId);


    /**
     * 查询所有角色列表
     * @return
     */
    List<Role> getRoles();


    /**
     * 查询当前登录用户所在项目的所有用户信息列表
     */
    List<User> getUsers(Integer projectId);


    /**
     * 获取所有权限集合
     */
    List<Module> allModule();


    /**
     * 修改用户权限的方法
     * @param userId
     * @param roleId
     * @param ids
     * @return
     */
    int editUserModule(Integer userId,Integer roleId,List<String> ids);


}
