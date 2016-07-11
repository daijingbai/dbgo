package dbgo.system.service;

import java.util.List;
import java.util.Map;

import dbgo.system.bean.Resource;
import dbgo.system.util.common.TreeJson;

public interface ResourceService {
    int deleteByPrimaryKey(Long resourceId) throws Exception;

    int insert(Resource record) throws Exception;

    Map<String,Object> findByPage(Resource record, int startPage, int endPage) throws Exception;

    int updateByPrimaryKeySelective(Resource record) throws Exception;
    
    public List<Resource> findPermissionListByUserCode(String usercode) throws Exception;
    
    public List<TreeJson> findMenusByUserCode(String usercode) throws Exception;
}