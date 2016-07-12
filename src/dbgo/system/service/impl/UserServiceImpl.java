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
    	//1.判断用户编号是否重复
    	UserExample example=new UserExample();
    	example.createCriteria().andUserCodeEqualTo(record.getUserCode());
    	List<User> users=userMapper.selectByExample(example);
    	if(users.size()>0){
    		throw new CustomException(getString("UserError.1", record.getUserCode()));
    	}
    	//生成盐
    	String str = "";  
	    Random random = new Random();  
	    for (int i = 0; i < 5; i++) {  
	        boolean b = random.nextBoolean();  
	        if (b) { // 字符串  
	            // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母  
	            str += (char) (97 + random.nextInt(26));// 取得大写字母  
	        } else { // 数字  
	            str += String.valueOf(random.nextInt(10));  
	        }  
	    } 
	    record.setSalt(str);
	    
	    //生成密码
        Md5Hash md5Hash = new Md5Hash(record.getUserCode(), str, 1);
        record.setPwd(md5Hash.toString());
        record.setStatus((short)0);
        return userMapper.insert(record);
    }

    public Map<String,Object> findByPage(User record, int startPage, int endPage) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        UserExample example=new UserExample();
        //具体条件查询请自行处理！！！
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
    	//1.查看是否改变了用户编号
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
     * 描述：根据用户编号获取用户信息
     * 作者：戴辉辉
     * 日期：2016-6-6
     * 版本：v1.0
     * 参数：usercode用户编号
     */
	@Override
	public User selectByUserCode(String usercode) throws Exception {
		UserExample example =new UserExample();
		example.createCriteria().andUserCodeEqualTo(usercode);
		List<User> lists=userMapper.selectByExample(example);
		if(lists.size()==0){//如果没有取到相应的值
			throw new RuntimeException(getString("UserError.0", usercode));
		}else{
			return lists.get(0);
		}
	}

	/**
	 * 根据用户编号查询资源
	 */
	@Override
	public List<Resource> findPermissionListByUserCode(String usercode) throws Exception {
		UserExample example =new UserExample();
		example.createCriteria();
		userMapper.selectByExample(example);
		return null;
	}
}