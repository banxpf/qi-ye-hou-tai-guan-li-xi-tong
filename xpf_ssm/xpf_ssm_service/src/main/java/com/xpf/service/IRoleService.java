package com.xpf.service;

import com.xpf.domian.Permission;
import com.xpf.domian.Role;

import java.util.List;

public interface IRoleService {

   public List<Role> findAll(int page,int size) throws Exception;

    public void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findRoleByIdAndAllPermission(String roleId)throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

    void deleteRole(String id) throws Exception;
}
