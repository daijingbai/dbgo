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
 * �Զ���Realm
* @author ���Ի� 
* @date Jun 17, 2016 3:16:58 PM 
* @version V1.0
 */
public class CustomRealm extends AuthorizingRealm{
	@Resource(name="userServiceImpl")
	private UserService userServiceImpl;
	
	@Resource(name="resourceServiceImpl")
	private ResourceService resourceServiceImpl;
	
	// ����realm������
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}
	
	//��Ȩ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//�� principals��ȡ�������Ϣ
		//��getPrimaryPrincipal��������ֵתΪ��ʵ������ͣ����ϱߵ�doGetAuthenticationInfo��֤ͨ����䵽SimpleAuthenticationInfo��������ͣ���
		User activeUser =  (User) principals.getPrimaryPrincipal();
		
		//���������Ϣ��ȡȨ����Ϣ
		//�����ݿ��ȡ��Ȩ������
		List<dbgo.system.bean.Resource> permissionList = null;
		try {
			permissionList = resourceServiceImpl.findPermissionListByUserCode(activeUser.getUserCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> permissions = new ArrayList<String>();
		if(permissionList!=null){
			for(dbgo.system.bean.Resource sysPermission:permissionList){
				//�����ݿ��е�Ȩ�ޱ�ǩ �����뼯��
				permissions.add(sysPermission.getCode());
			}
		}
						
		//�鵽Ȩ�����ݣ�������Ȩ��Ϣ(Ҫ���� �ϱߵ�permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//���ϱ߲�ѯ����Ȩ��Ϣ��䵽simpleAuthorizationInfo������
		simpleAuthorizationInfo.addStringPermissions(permissions);

		return simpleAuthorizationInfo;
	}

	//��֤
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token���û�������û��������� 
		// ��һ����token��ȡ���û���
		String userCode = (String) token.getPrincipal();
		
		// �ڶ����������û������userCode�����ݿ��ѯ
		User user = null;
		try {
			user = userServiceImpl.selectByUserCode(userCode);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// �����ѯ��������null
		if(user==null){//
			return null;
		}
		
		// �����ݿ��ѯ������
		String password = user.getPwd();
		
		//��
		String salt = user.getSalt();
		
		//activeUser�����û������Ϣ
		User activeUser=new User();
		activeUser.setPwd(password);
		activeUser.setUserId(user.getUserId());
		activeUser.setSalt(salt);
		activeUser.setUserName(user.getUserName());
		activeUser.setUserCode(user.getUserCode());
		//....
		
		//��activeUser����simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				activeUser, password,ByteSource.Util.bytes(salt), this.getName());
		return simpleAuthenticationInfo;
	}

	//�������
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
}
