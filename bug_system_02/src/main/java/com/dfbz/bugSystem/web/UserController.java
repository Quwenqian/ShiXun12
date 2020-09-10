package com.dfbz.bugSystem.web;


import com.alibaba.fastjson.JSONObject;
import com.dfbz.bugSystem.po.Module;
import com.dfbz.bugSystem.po.Role;
import com.dfbz.bugSystem.po.User;
import com.dfbz.bugSystem.service.RedisService;
import com.dfbz.bugSystem.service.UserService;
import com.dfbz.bugSystem.util.ResultBody;
import com.dfbz.bugSystem.vo.ModuleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    RedisService redisService;



    /*@RequiresRoles("admin")*/
    @RequiresPermissions("7000")
    @RequestMapping("/findUser")
    @ResponseBody
    public Object findUserById(@RequestParam("id") Integer id){
        System.out.println("doing findUser by Id");
        User u=null;
        if(redisService.get("user_"+id)!=null){
            JSONObject jsonObject= (JSONObject) redisService.get("user_"+id);
            u = JSONObject.parseObject(jsonObject.toJSONString(),User.class);
        }else{
            u = userService.findById(id);
            redisService.set("user_"+id,u);//向redis缓存存放数据
        }


        return JSONObject.toJSONString(u);

    }

    @RequestMapping("/login")
    public String login(){
        return "buglogin";
    }


    @RequestMapping("/doLogin")
    @ResponseBody
    public String login(User user,Boolean rememberMe, HttpSession session){

        UsernamePasswordToken token =
                new UsernamePasswordToken(user.getUserName(),user.getPassword(),rememberMe);

        Subject subject = SecurityUtils.getSubject();

        try{

            subject.login(token);
            return "success";

        } catch (UnknownAccountException e){
            return e.getMessage();
        } catch (LockedAccountException e){
            return e.getMessage();
        } catch (AuthorizationException e){
            return "认证失败";
        }


    }


    /**
     * 添加用户方法
     * @param user
     * @return
     */
    @RequiresPermissions("7000")
    @RequestMapping("/addUser")
    @ResponseBody
    public ResultBody addUser(User user){
        User loginUser = (User)SecurityUtils.getSubject().getPrincipal();
        user.setProjectId(loginUser.getProjectId());
        user.setRegisterId(loginUser.getUserId()+"");
        user.setRegisterDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        user.setDelFlag(0);
        int r = userService.addUser(user);
        return ResultBody.success(r>0 ? true :false);
    }


    /**
     * 查询所有角色列表
     */
    @RequestMapping("/mstAddUser")
    public String  showRoleList(Model model){
        List<Role> roles = userService.getRoles();
        model.addAttribute("roles",roles);
        return "mstAddUser";
    }

    /**
     * 显示当前项目的所有用户信息列表
     * @return
     */
    @RequiresPermissions("7000")
    @RequestMapping("/mstShowUsers")
    public String showUsers(Model model){
        User loginUser = (User)SecurityUtils.getSubject().getPrincipal();

        Page page = PageHelper.startPage(1,2);

        List<User> userList = userService.getUsers(loginUser.getProjectId());

        System.out.println("共"+page.getTotal()+"条记录，当前为"+page.getStartRow()+"-"+page.getEndRow()+"条记录");

        model.addAttribute("userList", userList);

        return "mstShowUsers";
    }


    /**
     * 显示某用户的所有权限更新页面
     * @param userId
     * @param roleId
     * @param model
     * @return
     */
    @RequiresPermissions("7000")
    @RequestMapping("/showModules")
    public String showModules(Integer userId,Integer roleId,Model model){
        //根据用户ID获得当前用户已有的所有权限列表
        List<Module> userModules=userService.getUserModules(userId);
        //获取moudle中所有权限的列表
        List<Module> moduleList = userService.allModule();

        //数据处理
        List<ModuleVO> userModulesFlag = new ArrayList<>();
        for (int i = 0; i < moduleList.size(); i++) {

            boolean flag = false;

            for (int j = 0; j < userModules.size(); j++) {
                if(moduleList.get(i).getModuleId().intValue() == userModules.get(j).getModuleId().intValue()){
                    flag = true;
                }
            }

            userModulesFlag.add(new ModuleVO(moduleList.get(i).getModuleId(),
                    moduleList.get(i).getModuleName(),flag));

        }

        model.addAttribute("userId",userId);
        model.addAttribute("roleId",roleId);
        model.addAttribute("modules",userModulesFlag);

        return "mstChangeModules";
    }

    /**
     * 根据用户ID，修改用户权限
     * @param userId
     * @param moduleIds
     * @return
     */
    @RequiresPermissions(value={"7000"})
    @RequestMapping("/changeUserModules")
    @ResponseBody
    public ResultBody changeUsreModules(@RequestParam("userId") Integer userId,@RequestParam("roleId") Integer roleId,
                                        @RequestParam("moduleIds[]")List<String> moduleIds){

        userService.editUserModule(userId,roleId,moduleIds);
        return ResultBody.success();
    }


}
