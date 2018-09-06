package com.wjx.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wjx.entity.Moudle;
import com.wjx.entity.User;
import com.wjx.service.UserService;

import test.MyRealm;

@Controller
public class TestController {
	
	//ini配置 iniRealm
	public void login(){
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("test","123");
		try {
			subject.login(token);
			System.out.println("登录成功");
			if(subject.isPermitted("add")){
				System.out.println("有add权限");
			}
		} catch (AuthenticationException e) {
			System.out.println("用户名或密码错误");
		}
	}
	
	//自定义realm
	public void myLogin(){
		DefaultSecurityManager securityManager=new DefaultSecurityManager();
		ModularRealmAuthenticator authenticator=new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);
		
		ModularRealmAuthorizer authorizer=new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		securityManager.setAuthorizer(authorizer);
		securityManager.setRealm(new MyRealm());
		
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("test","123");
		try {
			subject.login(token);
			System.out.println("登录成功");
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			System.out.println("登录失败");
		}
	}
	
	public void dbLogin(){
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-mysql.ini");
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("admin","123456");
		try {
			subject.login(token);
			System.out.println("登录成功");
			
		} catch (AuthenticationException e) {
			System.out.println("用户名或密码错误");
		}
	}
		
	
}
