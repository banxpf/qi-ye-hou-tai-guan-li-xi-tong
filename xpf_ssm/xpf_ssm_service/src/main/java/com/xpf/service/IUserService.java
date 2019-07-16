package com.xpf.service;

import com.xpf.domian.Orders;
import com.xpf.domian.Role;
import com.xpf.domian.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService{


    public List<UserInfo> findAll(int page,int size) throws Exception;

    void save(UserInfo userInfo)throws Exception;

    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRoles(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleId) throws Exception;

    void deleteUser(String id) throws Exception;


}
