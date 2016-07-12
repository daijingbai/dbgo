package dbgo.system.service.impl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static dbgo.system.util.messages.Messages.getString;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dbgo.system.bean.Resource;
import dbgo.system.bean.User;
import dbgo.system.bean.UserExample;
import dbgo.system.bean.UserExample.Criteria;
import dbgo.system.dao.UserMapper;
import dbgo.system.exception.CustomException;
import dbgo.system.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	public void deleteByPrimaryKey(String[] ids) throws Exception {
		for (String id : ids) {
			userMapper.deleteByPrimaryKey(new BigDecimal(id));
		}
    }

    public int insert(User record) throws Exception {
    	//1.�ж��û�����Ƿ��ظ�
    	UserExample example=new UserExample();
    	example.createCriteria().andUserCodeEqualTo(record.getUserCode());
    	List<User> users=userMapper.selectByExample(example);
    	if(users.size()>0){
    		throw new CustomException(getString("UserError.1", record.getUserCode()));
    	}
    	//������
    	String str = "";  
	    Random random = new Random();  
	    for (int i = 0; i < 5; i++) {  
	        boolean b = random.nextBoolean();  
	        if (b) { // �ַ���  
	            // int choice = random.nextBoolean() ? 65 : 97; ȡ��65��д��ĸ����97Сд��ĸ  
	            str += (char) (97 + random.nextInt(26));// ȡ�ô�д��ĸ  
	        } else { // ����  
	            str += String.valueOf(random.nextInt(10));  
	        }  
	    } 
	    record.setSalt(str);
	    
	    //��������
        Md5Hash md5Hash = new Md5Hash(record.getUserCode(), str, 1);
        record.setPwd(md5Hash.toString());
        record.setStatus((short)0);
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
    	//1.�鿴�Ƿ�ı����û����
    	UserExample example=new UserExample();
    	example.createCriteria().andUserCodeEqualTo(record.getUserCode())
    		.andUserIdEqualTo(record.getUserId());
    	List<User> users=userMapper.selectByExample(example);
    	if(users.size()==0){
    		throw new CustomException(getString("UserError.2"));
    	}
    	
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