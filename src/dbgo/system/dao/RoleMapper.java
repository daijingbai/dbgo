package dbgo.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dbgo.system.bean.Role;
import dbgo.system.bean.RoleExample;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int batchInsert(List records);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    List<Role> selectByExampleWithPage(@Param("example") RoleExample example, @Param("startPage") int startPage, @Param("endPage") int endPage);

    Role selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}