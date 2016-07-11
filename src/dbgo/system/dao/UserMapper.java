package dbgo.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dbgo.system.bean.User;
import dbgo.system.bean.UserExample;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    List<User> selectByExampleWithPage(@Param("example") UserExample example, @Param("startPage") int startPage, @Param("endPage") int endPage);

    User selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}