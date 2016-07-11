package dbgo.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dbgo.system.bean.Resource;
import dbgo.system.bean.ResourceExample;

public interface ResourceMapper {
    int countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Long resourceId);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    List<Resource> selectByExampleWithPage(@Param("example") ResourceExample example, @Param("startPage") int startPage, @Param("endPage") int endPage);

    Resource selectByPrimaryKey(Long resourceId);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}