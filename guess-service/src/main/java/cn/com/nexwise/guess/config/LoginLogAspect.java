package cn.com.nexwise.guess.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoginLogAspect {
	/**
	 * log4j日志管理器
	 */
	public static final Logger logger = LoggerFactory.getLogger(LoginLogAspect.class);
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	/**
	 * 用户登录日志记录
	 */
	@Pointcut("execution(public * cn.com.nexwise.guess.controller.*.login(..)) ")
	public void loginLog(){}
    
	@Before("loginLog()")
	public void doLoginBefore(JoinPoint joinPoint){

	}
	
	@AfterReturning(returning = "result", pointcut = "loginLog()")
	public void doLoginAfterReturning(Object result){

	}
}