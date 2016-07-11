package dbgo.system.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static dbgo.system.util.messages.Messages.getString;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbgo.system.bean.Resource;
import dbgo.system.bean.User;
import dbgo.system.bean.UserExample;
import dbgo.system.bean.UserExample.Criteria;
import dbgo.system.dao.UserMapper;
import dbgo.system.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	 UserMapper userMapper;
	
	public int deleteByPrimaryKey(Long userId) throws Exception {
        return userMapper.deleteByPrimaryKey(userId);
    }

    public int insert(User record) throws Exception {
        return userMapper.insert(record);
    }

    public Map<String,Object> findByPage(User record, int startPage, int endPage) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        UserExample example=new UserExample();
        //����������ѯ�����д�������
        Criteria criteria=example.createCriteria();
        if(StringUtils.isNotEmpty(record.getUserName())){
        	criteria.andUserNameEqualTo(record.getUserName());
        }
        if(StringUtils.isNotEmpty(record.getUserCode())){
        	criteria.andUserCodeEqualTo(record.getUserCode());
        }
        map.put("rows", userMapper.selectByExampleWithPage(example,startPage,endPage));
        map.put("total", userMapper.countByExample(example));
        return map;
    }

    public int updateByPrimaryKeySelective(User record) throws Exception {
        return userMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * �����������û���Ż�ȡ�û���Ϣ
     * ���ߣ����Ի�
     * ���ڣ�2016-6-6
     * �汾��v1.0
     * ������usercode�û����
     */
	@Override
	public User selectByUserCode(String usercode) throws Exception {
		UserExample example =new UserExample();
		example.createCriteria().andUserCodeEqualTo(usercode);
		List<User> lists=userMapper.selectByExample(example);
		if(lists.size()==0){//���û��ȡ����Ӧ��ֵ
			throw new RuntimeException(getString("UserError.0", usercode));
		}else{
			return lists.get(0);
		}
	}

	/**
	 * �����û���Ų�ѯ��Դ
	 */
	@Override
	public List<Resource> findPermissionListByUserCode(String usercode) throws Exception {
		UserExample example =new UserExample();
		example.createCriteria();
		userMapper.selectByExample(example);
		return null;
	}
}