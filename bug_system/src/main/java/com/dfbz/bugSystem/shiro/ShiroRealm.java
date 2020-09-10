package com.dfbz.bugSystem.shiro;

import com.dfbz.bugSystem.po.Module;
import com.dfbz.bugSystem.po.Role;
import com.dfbz.bugSystem.po.User;
import com.dfbz.bugSystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;




    /**
     * 用于进行用户角色获取和权限校验
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获得用户角色集合
        List<Role> roles = userService.getUserRoles(user.getUserId());
        Set<String> roleSet=null;
        Set<String> moduleSet=null;
        if(roles.size()>0 && roles.get(0)!=null){
             roleSet = new HashSet<>();
            for (Role r:roles ) {
                roleSet.add(r.getRoleName());
            }
        }

        //获取用户权限集合
        List<Module> modules = userService.getUserModules(user.getUserId());
        if(modules.size()>0 && modules.get(0)!=null){
            moduleSet = new HashSet<>();
            for (Module m:modules ) {
                moduleSet.add(m.getModuleId()+"");
            }

        }


        //为权限校验器接口设置角色集合及权限集合
        simpleAuthorizationInfo.setRoles(roleSet);
        simpleAuthorizationInfo.setStringPermissions(moduleSet);


        return simpleAuthorizationInfo;
    }

    /**
     * 用于进行用户登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户名和密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());


        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);

        User loginUser = userService.login(user);

        if(loginUser == null){
            throw new UnknownAccountException("用户名或密码错误！");
        }else if(loginUser.getDelFlag()==1){
            throw new LockedAccountException("您的账号已被锁定，请联系管理员！");
        }

        System.out.println("登陆后："+loginUser.toString());

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginUser,password,userName);

        return info;
    }
}
