package dbgo.system.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbgo.system.bean.Role;
import dbgo.system.bean.RoleExample;
import dbgo.system.dao.RoleMapper;
import dbgo.system.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    public int deleteByPrimaryKey(Long roleId) throws Exception {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    public int insert(Role record) throws Exception {
        return roleMapper.insert(record);
    }

    public Map<String,Object> findByPage(Role record, int startPage, int endPage) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        RoleExample example=new RoleExample();
        //具体条件查询请自行处理！！！
        if(StringUtils.isNotEmpty(record.getName())){
        	example.createCriteria().andNameEqualTo(record.getName());
        }
        map.put("rows", roleMapper.selectByExampleWithPage(example,startPage,endPage));
        map.put("total", roleMapper.countByExample(example));
        return map;
    }

    public int updateByPrimaryKeySelective(Role record) throws Exception {
        return roleMapper.updateByPrimaryKeySelective(record);
    }
}