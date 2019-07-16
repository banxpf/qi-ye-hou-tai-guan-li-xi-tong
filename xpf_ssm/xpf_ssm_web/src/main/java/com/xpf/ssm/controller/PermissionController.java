package com.xpf.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.xpf.dao.IPermissionDao;
import com.xpf.domian.Permission;
import com.xpf.domian.Role;
import com.xpf.service.IPermissionService;
import com.xpf.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    //查询所有用户
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size)throws Exception{

        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("permissionList",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deletePermission.do")
    public String deletePermission(String id) throws Exception {
        permissionService.deletePermission(id);
        return "redirect:findAll.do";

    }

}
