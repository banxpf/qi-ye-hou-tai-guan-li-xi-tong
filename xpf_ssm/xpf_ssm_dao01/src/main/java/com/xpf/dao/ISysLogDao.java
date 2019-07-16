package com.xpf.dao;

import com.xpf.domian.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;


    @Select("select* from sysLog")
    List<SysLog> findAll() throws Exception;

    @Delete("delete from sysLog where id =#{id}")
    void deleteSysLog(String  id) throws Exception;
}
