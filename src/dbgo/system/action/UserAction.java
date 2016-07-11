package dbgo.system.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import dbgo.system.bean.User;
import dbgo.system.service.UserService;

/**
 * 用户控制层模版
* @author 戴辉辉 
* @date Jul 5, 2016 11:44:40 AM 
* @version V1.0
 */
@Controller
@RequestMapping(value="/User")
public class UserAction {
	@Autowired
    UserService userServiceImpl;
	
	@RequestMapping(value="/Index.do")
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
}
