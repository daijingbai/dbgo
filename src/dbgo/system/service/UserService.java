package dbgo.system.service;

import java.util.List;
import java.util.Map;

import dbgo.system.bean.Resource;
import dbgo.system.bean.User;

public interface UserService {
	void deleteByPrimaryKey(String[] ids) throws Exception;

    int insert(User record) throws Exception;

    Map<String,Object> findByPage(User record, int startPage, int endPage) throws Exception;

    int updateByPrimaryKeySelective(User record) throws Exception;
    
    /**
     * 根据用户编码查询用户
     */
    public User selectByUserCode(String usercode) throws Exception;
    
    /**
     * 根据用户编码查找相应的权限
     */
    public List<Resource> findPermissionListByUserCode(String usercode) throws Exception;
}
