package dbgo.test.utils;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dbgo.system.service.ResourceService;

public class MD5Test extends SpringJunitTest{
	@Autowired
	ResourceService resourceServiceImpl;

	public static void main(String[] args) {
		//ԭʼ ���� 
		String source = "admin";
		//��
		String salt = "qwerty";
		//ɢ�д���
		int hashIterations = 1;
		
		//���췽���У�
		//��һ�����������ģ�ԭʼ���� 
		//�ڶ����������Σ�ͨ��ʹ�������
		//������������ɢ�еĴ���������ɢ�����Σ��൱ ��md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		
		String password_md5 =  md5Hash.toString();
		System.out.println(password_md5);
		//��һ��������ɢ���㷨 
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println(simpleHash.toString());
	}
	
	@Test
	public void test() throws Exception{
		List<dbgo.system.bean.Resource> res=resourceServiceImpl.findPermissionListByUserCode("110");
		System.out.println(res.size());
	}
}
