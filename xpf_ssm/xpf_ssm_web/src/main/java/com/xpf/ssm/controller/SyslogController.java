package com.xpf.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.xpf.domian.SysLog;
import com.xpf.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLogs")
public class SyslogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "10")Integer size) throws Exception {
        ModelAndView mv= new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(sysLogs);
        mv.addObject("sysLogs",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
    @RequestMapping("/deleteSysLog.do")
    public String deleteSysLog(String id) throws Exception {
        String[] splitStr=id.split(",");
        for (int i=0;i<splitStr.length;++i){

            sysLogService.deleteSysLog(splitStr[i]);

        }
        return "redirect:findAll.do";

    }


}
