package com.xpf.ssm.controller;


import com.xpf.domian.SysLog;
import com.xpf.service.ISysLogService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.SoundbankResource;

import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;
    //前置通知 主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.xpf.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception{
            visitTime =new Date();          //当前时间就是开始访问的时间

            clazz = jp.getTarget().getClass();  //具体要访问的类

                if (clazz.getName().equals("com.xpf.ssm.controller.SyslogController")){
                    clazz= null;

                }else {
                    String methodName = jp.getSignature().getName();  //获取访问的方法名称
                    Object[] args = jp.getArgs();


                    //获取具体执行的方法
                    if (args == null || args.length == 0) {

                        method = clazz.getMethod(methodName);  //只能获取无参数的方法
                    } else {
                        Class[] classArgs = new Class[args.length];
                        for (int i = 0; i < args.length; ++i) {
                            classArgs[i] = args[i].getClass();
                        }

                        method = clazz.getMethod(methodName, classArgs);  //获取有参方法
                    }
                }
    }


//    后置通知
    @After("execution(* com.xpf.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
         Long time = new Date().getTime() - visitTime.getTime();//获取访问时长

        String url="";
       //获取url
        if (clazz != null && method !=null && clazz!= LogAop.class){

            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                String[] classValue = classAnnotation.value();

                //获取方法上的@RequestMapping（xxx）
                RequestMapping methodAnnotation = (RequestMapping) method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){

                    String[] methodValue =methodAnnotation.value();
                    url =classValue[0]+methodValue[0];

                    //获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext(); //从上下文中获取了当前登陆的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到sysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    sysLogService.save(sysLog);


                }


            }

        }






    }





}
