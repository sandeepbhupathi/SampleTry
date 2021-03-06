package com.operations.client.aspect;

import java.time.Duration;
import java.time.LocalTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
	
	 private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	
	@Around("execution(* com.operations.client.service.OperationService.perform*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		LocalTime beforeEntring = LocalTime.now();
		logger.info("Entering Method: "+joinPoint.getSignature().toShortString()+" : "+beforeEntring);
		Object returnVal = joinPoint.proceed();
		LocalTime afterExiting = LocalTime.now();
		logger.info("Exiting Method: "+joinPoint.getSignature().toShortString()+" : "+afterExiting);
		logger.info("Total Time Taken: "+joinPoint.getSignature().toShortString()+" : "+Duration.between(beforeEntring,afterExiting).toMillis());
		return returnVal;
	}

}
