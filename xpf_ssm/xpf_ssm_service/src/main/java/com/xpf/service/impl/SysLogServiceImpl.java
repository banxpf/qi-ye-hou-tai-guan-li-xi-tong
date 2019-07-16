package com.xpf.service.impl;

import com.github.pagehelper.PageHelper;
import com.xpf.dao.ISysLogDao;
import com.xpf.domian.SysLog;
import com.xpf.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
   private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }

    @Override
    public void deleteSysLog(String  id) throws Exception {
        sysLogDao.deleteSysLog(id);
    }
}
