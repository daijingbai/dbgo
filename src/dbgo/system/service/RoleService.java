package dbgo.system.service;

import java.util.Map;

import dbgo.system.bean.Role;

public interface RoleService {
    int deleteByPrimaryKey(Long roleId) throws Exception;

    int insert(Role record) throws Exception;

    Map<String,Object> findByPage(Role record, int startPage, int endPage) throws Exception;

    int updateByPrimaryKeySelective(Role record) throws Exception;
}