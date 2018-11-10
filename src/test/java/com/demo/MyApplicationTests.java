package com.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;
import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyApplicationTests {

	@Test
	public void contextLoads() {

	}

	@Test
	public void testPermission() {

		// 从ini文件中创建SecurityManager工厂
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");

		//创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		//将securityManager设置到运行环境
		SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);

		//创建主体对象
		Subject subject = SecurityUtils.getSubject();

		//对主体对象进行认证
		//用户登录
		// 设置用户认证的身份(principals)和凭证(credentials)
		UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");

		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		// 用户认证状态
		Boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("用户认证状态：" + isAuthenticated);

		// 用户授权检测 基于角色授权
		// 是否有某一个角色
		System.out.println("用户是否拥有一个角色：" + subject.hasRole("role1"));
		// 是否有多个角色
		System.out.println("用户是否拥有多个角色：" + subject.hasAllRoles(Arrays.asList("role1", "role2")));

//		subject.checkRole("role1");
//		subject.checkRoles(Arrays.asList("role1", "role2"));

		// 授权检测，失败则抛出异常
		// subject.checkRole("role22");

		// 基于资源授权
		System.out.println("是否拥有某一个权限：" + subject.isPermitted("user:delete"));
		System.out.println("是否拥有多个权限：" + subject.isPermittedAll("user:create:1", "user:delete"));

		//检查权限
		subject.checkPermission("sys:user:delete");
		subject.checkPermissions("user:create:1", "user:delete");

	}


	/**
	 * 盐加密测试
	 */
	@Test
	public void testPwdSalt() {
		String algorithmName = "md5"; //加密方式
		String username = "xiaoyun";  //用户名
		String password = "admin";    //密码
		String salt1 = username;

		//加密
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2; //加密的次数
		SimpleHash hash = new SimpleHash(algorithmName, password,
				salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println("密码----->加密后的值----->" + encodedPassword);
		System.out.println("随机数当作盐----->加密后的值----->" + salt2);

	}

}