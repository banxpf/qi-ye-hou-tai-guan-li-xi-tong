package com.xpf.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.xpf.domian.Permission;
import com.xpf.domian.Role;
import com.xpf.domian.UserInfo;
import com.xpf.service.IRoleService;
import com.xpf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    //查询所有用户
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size)throws Exception{

        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roles);
        mv.addObject("roleList",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }
    //添加角色
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }
    //根据roleId查询role，并查询可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role=roleService.findById(roleId);
        List<Permission> otherPermission = roleService.findRoleByIdAndAllPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermission);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true)String[] permissionIds) throws Exception {

        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";

    }

    @RequestMapping("/deleteRole.do")
    public String deleteRole(String id) throws Exception {
        roleService.deleteRole(id);
        return "redirect:findAll.do";

    }
}
