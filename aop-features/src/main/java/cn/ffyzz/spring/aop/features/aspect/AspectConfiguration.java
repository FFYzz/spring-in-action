package cn.ffyzz.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.Random;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description: Aspect 配置类
 */
@Aspect
@Order
public class AspectConfiguration {

    /**
     * 匹配 join point 的规则定义
     */
    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod() {
    }

    @Around("anyPublicMethod()")
    private Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around any public method..");
        return pjp.proceed();
    }

    /**
     * 具体的 join point 拦截动作
     */
    @Before("anyPublicMethod()")
    private void beforeAnyPublicMethod() {
        System.out.println("@Before any public method..");
    }

    @AfterReturning("anyPublicMethod()")
    private void afterReturningAnyPublicMethod() {
        System.out.println("@AfterReturning any public method..");
    }

    @After("anyPublicMethod()")
    private void afterAnyPublicMethod() {
        System.out.println("@After any public method..");
    }

    @AfterThrowing("anyPublicMethod()")
    private void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method..");
    }

}
