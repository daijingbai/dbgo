package dbgo.test.utils;

import java.util.List;
import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dbgo.system.service.ResourceService;

public class MD5Test extends SpringJunitTest{
	@Autowired
	ResourceService resourceServiceImpl;

	public static void main(String[] args) {
		//原始 密码 
		String source = "admin";
		//盐
		String salt = "qwerty";
		//散列次数
		int hashIterations = 1;
		
		//构造方法中：
		//第一个参数：明文，原始密码 
		//第二个参数：盐，通过使用随机数
		//第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		
		String password_md5 =  md5Hash.toString();
		System.out.println(password_md5);
		//第一个参数：散列算法 
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println(simpleHash.toString());
	}
	
	@Test
	public void test() throws Exception{
		List<dbgo.system.bean.Resource> res=resourceServiceImpl.findPermissionListByUserCode("110");
		System.out.println(res.size());
	}
	
	@Test
	public void test1(){
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
	    System.out.println(str);
	}
}
