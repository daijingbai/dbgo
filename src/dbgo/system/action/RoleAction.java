package dbgo.system.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dbgo.system.bean.Role;
import dbgo.system.service.RoleService;

@Controller
@RequestMapping(value="/Role")
public class RoleAction {
    @Autowired
    RoleService roleServiceImpl;

    @RequestMapping(value="/index.do")
    public String index() {
        return "role";
    }

    @RequestMapping(value="/delete.do")
    @ResponseBody
    public Map<String,Object> delete(Long roleId) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        roleServiceImpl.deleteByPrimaryKey(roleId);
        map.put("success", true);
        map.put("msg", "自行输入成功信息");
        return map;
    }

    @RequestMapping(value="/insert.do")
    @ResponseBody
    public Map<String,Object> insert(Role record) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        roleServiceImpl.insert(record);
        map.put("success", true);
        map.put("msg", "插入角色信息成功");
        return map;
    }

    @RequestMapping(value="/findByPage.do")
    @ResponseBody
    public Map<String,Object> findByPage(Role record, int rows, int page) throws Exception {
        int startPage=rows*(page-1)+1;
        int endPage=rows*page;
        return roleServiceImpl.findByPage(record,startPage,endPage);
    }

    @RequestMapping(value="/update.do")
    @ResponseBody
    public Map<String,Object> update(Role record) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        roleServiceImpl.updateByPrimaryKeySelective(record);
        map.put("success", true);
        map.put("msg", "角色信息更新成功");
        return map;
    }
}