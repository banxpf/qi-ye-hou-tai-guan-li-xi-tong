package com.xpf.service;

import com.xpf.domian.Permission;

import java.util.List;

public interface IPermissionService {


    public List<Permission> findAll(int page,int size) throws Exception;

    public void save(Permission permission) throws Exception;

    void deletePermission(String id) throws Exception;
}
