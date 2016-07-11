package dbgo.system.action;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dbgo.system.bean.Resource;
import dbgo.system.bean.User;
import dbgo.system.service.ResourceService;
import dbgo.system.util.common.TreeJson;

@Controller
@RequestMapping(value="/Resource")
public class ResourceAction {
    @Autowired
    ResourceService resourceServiceImpl;

    @RequestMapping(value="/delete.do")
    public void delete(Long resourceId) throws Exception {
        resourceServiceImpl.deleteByPrimaryKey(resourceId);
    }

    @RequestMapping(value="/insert.do")
    public void insert(Resource record) throws Exception {
        resourceServiceImpl.insert(record);
    }

    @RequestMapping(value="/findByPage.do")
    @ResponseBody
    public Map<String,Object> findByPage(Resource record, int rows, int page) throws Exception {
        int startPage=rows*(page-1)+1;
        int endPage=rows*page;
        return resourceServiceImpl.findByPage(record,startPage,endPage);
    }

    @RequestMapping(value="/update.do")
    public void update(Resource record) throws Exception {
        resourceServiceImpl.updateByPrimaryKeySelective(record);
    }
    
    /**
     * @throws Exception 
     * ���ݵ�ǰ�û���ȡ�˵�
    * @Title: getMenus 
    * @Description: TODO(������һ�仰�����������������) 
    * @param @return    �趨�ļ� 
    * @return List<Resource>    �������� 
    * @throws
     */
    @RequestMapping(value="/getMenus.do")
    public @ResponseBody List<TreeJson> getMenus() throws Exception{
    	//��shiro��session��ȡactiveUser
		Subject subject = SecurityUtils.getSubject();
		//ȡ�����Ϣ
		User activeUser =(User) subject.getPrincipal();
		List<TreeJson> res=resourceServiceImpl.findMenusByUserCode(activeUser.getUserCode());
    	return res;
    }
}