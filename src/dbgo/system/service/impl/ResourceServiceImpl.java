package dbgo.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbgo.system.bean.Resource;
import dbgo.system.bean.ResourceExample;
import dbgo.system.dao.ResourceMapper;
import dbgo.system.service.ResourceService;
import dbgo.system.util.common.TreeJson;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    public int deleteByPrimaryKey(Long resourceId) throws Exception {
        return resourceMapper.deleteByPrimaryKey(resourceId);
    }

    public int insert(Resource record) throws Exception {
        return resourceMapper.insert(record);
    }

    public Map<String,Object> findByPage(Resource record, int startPage, int endPage) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        ResourceExample example=new ResourceExample();
        //具体条件查询请自行处理！！！
        map.put("total", resourceMapper.selectByExampleWithPage(example,startPage,endPage));
        map.put("rows", resourceMapper.countByExample(example));
        return map;
    }

    public int updateByPrimaryKeySelective(Resource record) throws Exception {
        return resourceMapper.updateByPrimaryKeySelective(record);
    }

    /**
	 * 根据用户编号查询资源
	 */
	@Override
	public List<Resource> findPermissionListByUserCode(String usercode) throws Exception {
		ResourceExample example =new ResourceExample();
		example.createCriteria().andResourceIdIn("select lnk_role_res.res_id from lnk_role_res "+
		"left join lnk_user_role on lnk_role_res.role_id=lnk_user_role.role_id "+
				"left join tbl_user on lnk_user_role.user_id=tbl_user.user_id where tbl_user.user_code='"+usercode+"'")
		.andTypeEqualTo((short) 1);
		return resourceMapper.selectByExample(example);
	}

	/**
	 * 根据用户编号查询菜单
	 */
	@Override
	public List<TreeJson> findMenusByUserCode(String usercode) throws Exception {
		ResourceExample example =new ResourceExample();
		example.createCriteria().andResourceIdIn("select lnk_role_res.res_id from lnk_role_res "+
		"left join lnk_user_role on lnk_role_res.role_id=lnk_user_role.role_id "+
				"left join tbl_user on lnk_user_role.user_id=tbl_user.user_id where tbl_user.user_code='"+usercode+"'")
		.andTypeEqualTo((short) 0);
		List<TreeJson> tjs=new ArrayList<TreeJson>();
		List<dbgo.system.bean.Resource> res=resourceMapper.selectByExample(example);
		for (dbgo.system.bean.Resource resource : res) {
			TreeJson tj=new TreeJson();
			Map<String,String> attr=new HashMap<String,String>();
			tj.setId(resource.getResourceId().toString());
			tj.setPid(resource.getParentId()==null?"":resource.getParentId().toString());
			tj.setText(resource.getName());
			attr.put("url", resource.getUrl());
			tj.setAttributes(attr);
			tjs.add(tj);
		}
		
		return TreeJson.formatTree(tjs);
	}
}