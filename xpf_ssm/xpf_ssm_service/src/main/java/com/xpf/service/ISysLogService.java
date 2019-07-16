package com.xpf.service;

import com.xpf.domian.SysLog;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(int page,int size) throws Exception;

    void deleteSysLog(String  id) throws Exception;
}
