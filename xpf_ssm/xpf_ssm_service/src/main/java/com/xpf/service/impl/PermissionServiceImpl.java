package com.xpf.service.impl;

import com.github.pagehelper.PageHelper;
import com.xpf.dao.IPermissionDao;
import com.xpf.domian.Permission;
import com.xpf.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;


    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public void deletePermission(String id) throws Exception {
        permissionDao.deletePermission(id);
    }
}
