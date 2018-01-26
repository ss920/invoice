package com.systena.invoice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class InvoiceAop.
 */
@Aspect
@Component
@Slf4j
public class InvoiceAop {

	/**
	 * Method before.
	 *
	 * @param joinPoint the join point
	 * @throws Throwable the throwable
	 */
	@Before("execution(* com.systena.invoice.*..*.*(..))")
	public void methodBefore( JoinPoint joinPoint ) throws Throwable {
		log.info(joinPoint.getTarget().getClass().toString() + " - " + joinPoint.getSignature().getName() + " [START]");
    }

	/**
	 * Method after.
	 *
	 * @param joinPoint the join point
	 * @throws Throwable the throwable
	 */
	@After("execution(* com.systena.invoice.*..*.*(..))")
	public void methodAfter( JoinPoint joinPoint ) throws Throwable {
		log.info(joinPoint.getTarget().getClass().toString() + " - " + joinPoint.getSignature().getName() + " [END]");
    }


}
