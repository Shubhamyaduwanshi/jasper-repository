package com.csipl.lpg.logger;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckBeforAdvice {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.csipl.lpg..*)" + " || within(com.csipl.lpg.service..*)"
			+ " || within(com.csipl.lpg.controller..*)")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	@Pointcut("execution(* com.csipl.lpg.controller.LatterPadController.getLatterPad(..))")
	public void getIdPointCut() {

	}

	@Before("getIdPointCut()")
	public void checkId(JoinPoint joinPoint) throws Throwable {
		Object args[] = joinPoint.getArgs();
		log.debug("********************************User Id:" + args[0] + "********************************");
		if ((Long) args[0] <= 1)
			log.error("Invalid Id: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		else
			log.debug("Check Id Format: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	}

}
