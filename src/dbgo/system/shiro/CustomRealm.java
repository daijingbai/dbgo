package dbgo.system.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import dbgo.system.bean.User;
import dbgo.system.service.ResourceService;
import dbgo.system.service.UserService;

/**
 * 自定义Realm
* @author 戴辉辉 
* @date Jun 17, 2016 3:16:58 PM 
* @version V1.0
 */
public class CustomRealm extends AuthorizingRealm{
	@Resource(name="userServiceImpl")
	private UserService userServiceImpl;
	
	@Resource(name="resourceServiceImpl")
	private ResourceService resourceServiceImpl;
	
	// 设置realm的名称
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//从 principals获取主身份信息
		//将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		User activeUser =  (User) principals.getPrimaryPrincipal();
		
		//根据身份信息获取权限信息
		//从数据库获取到权限数据
		List<dbgo.system.bean.Resource> permissionList = null;
		try {
			permissionList = resourceServiceImpl.findPermissionListByUserCode(activeUser.getUserCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> permissions = new ArrayList<String>();
		if(permissionList!=null){
			for(dbgo.system.bean.Resource sysPermission:permissionList){
				//将数据库中的权限标签 符放入集合
				permissions.add(sysPermission.getCode());
			}
		}
						
		//查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);

		return simpleAuthorizationInfo;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token是用户输入的用户名和密码 
		// 第一步从token中取出用户名
		String userCode = (String) token.getPrincipal();
		
		// 第二步：根据用户输入的userCode从数据库查询
		User user = null;
		try {
			user = userServiceImpl.selectByUserCode(userCode);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// 如果查询不到返回null
		if(user==null){//
			return null;
		}
		
		// 从数据库查询到密码
		String password = user.getPwd();
		
		//盐
		String salt = user.getSalt();
		
		//activeUser就是用户身份信息
		User activeUser=new User();
		activeUser.setPwd(password);
		activeUser.setUserId(user.getUserId());
		activeUser.setSalt(salt);
		activeUser.setUserName(user.getUserName());
		activeUser.setUserCode(user.getUserCode());
		//....
		
		//将activeUser设置simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				activeUser, password,ByteSource.Util.bytes(salt), this.getName());
		return simpleAuthenticationInfo;
	}

	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
}
