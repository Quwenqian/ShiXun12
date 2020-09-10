package com.dfbz.bugSystem.service;

import com.dfbz.bugSystem.dao.*;
import com.dfbz.bugSystem.po.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserModuleMapper userModuleMapper;


    @Override
    public int addUser(User user) {
        userMapper.insertSelection(user);
        userRoleMapper.insert(new UserRole(user.getUserId(),user.getUserRole().getRoleId(),user.getRegisterId(),user.getRegisterDate(),user.getDelFlag()));
        return 1;
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> getUserRoles(Integer userId) {
        return userRoleMapper.findByUserId(userId);
    }

    @Override
    public List<Module> getUserModules(Integer userId) {
        return userModuleMapper.findByUserId(userId);
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.selectAll();
    }

    @Override
    public List<User> getUsers(Integer projectId) {
        return userMapper.selectAll(projectId);
    }

    @Override
    public List<Module> allModule() {
        return moduleMapper.selectAll();
    }

    @Override
    public int editUserModule(Integer userId, Integer roleId, List<String> ids) {

        //先删除用户权限数据
        userModuleMapper.deleteByPrimaryKey(userId);

        //再循环添加用户权限数据
        String registerId = ((User) SecurityUtils.getSubject().getPrincipal()).getUserName();
        String registerDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        for (String id:ids) {
            userModuleMapper.insert(new UserModule(roleId,Integer.parseInt(id),userId,registerId,registerDate));
        }


        return 1;
    }
}
