package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect // 해당 클래스의 객체가 Aspect를 구현한 것임으로 나타내기 위해서 사용합니다.
@Log4j 
@Component // AOP와는 관계가 없지만 스프링에서 빈으로 인식하기 위해서 사용합니다.
public class LogAdvice {

	
	
	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	//맨앞의 *는 접근제한자를 의미하고, 맨뒤의 *는 클래스의 이름과 메서드의 이름을 의미한다.
	public void  logBefore() {
		
		
		log.info("=========================");
	}
	
	
	//args를 이용한 파라미터 추적 // && args를 이용하는 설정은 간단히 파라미터를 찾아서 기록할때 에는 유용하지만 파라미터가 다른 여러 종류의 
	//메서드에 적용하는 데에는 간단하지 않다는 단점이 있다. 
	@Before("execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1 , String str2) {
		
		log.info("str1: " + str1);
		log.info("str2: " + str2);
	}
	
	// @AfterThrowing 어노테이션은 지정된 대상이 예외를 발생한 후에 동작하면서 문제를 찾을수 있도록 도와줄 수 있습니다.
	@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing = "exception")
	public void logException(Exception exception) {
		
		log.info("Exception.....!!!!");
		log.info("exception: " + exception);
	}
	
	
	//@Around 와 ProceedingJoinPoint
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		log.info("Target: " + pjp.getTarget() );
		log.info("Param: " + Arrays.toString(pjp.getArgs()));
		
		//invoke method
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIME: " + (end - start));
		
		return result;
	}
}
