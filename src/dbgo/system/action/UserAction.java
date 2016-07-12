package dbgo.system.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import dbgo.system.bean.User;
import dbgo.system.service.UserService;

/**
 * �û����Ʋ�ģ��
* @author ���Ի� 
* @date Jul 5, 2016 11:44:40 AM 
* @version V1.0
 */
@Controller
@RequestMapping(value="/User")
public class UserAction {
	@Autowired
    UserService userServiceImpl;
	
	@RequestMapping(value="/index.do")
	public String index(){
		return "user";
	}
	
    @RequestMapping(value="/findByPage.do")
    @ResponseBody
    public Map<String,Object> findByPage(User record, int rows, int page) throws Exception {
        int startPage=rows*(page-1)+1;
        int endPage=rows*page;
        return userServiceImpl.findByPage(record,startPage,endPage);
    }
    
    @RequestMapping(value="/insert.do")
    @ResponseBody
    public Map<String,Object> insert(User record) throws Exception {
    	Map<String,Object> map=new LinkedHashMap<String,Object>();
    	userServiceImpl.insert(record);
    	map.put("success", true);
    	map.put("msg", "�û�����ɹ�!");
    	return map;
    }
    
    @RequestMapping(value="/update.do")
    @ResponseBody
    public Map<String,Object> update(User record) throws Exception {
    	Map<String,Object> map=new LinkedHashMap<String,Object>();
    	userServiceImpl.updateByPrimaryKeySelective(record);
    	map.put("success", true);
    	map.put("msg", "�û����³ɹ�!");
    	return map;
    }
    
    @RequestMapping(value="/delete.do")
    @ResponseBody
    public Map<String,Object> delete(String ids) throws Exception {
    	Map<String,Object> map=new LinkedHashMap<String,Object>();
    	String[] id=ids.substring(0, ids.length()-1).split(",");
    	userServiceImpl.deleteByPrimaryKey(id);
    	map.put("success", true);
    	map.put("msg", "ɾ���ɹ�!");
    	return map;
    }
}
