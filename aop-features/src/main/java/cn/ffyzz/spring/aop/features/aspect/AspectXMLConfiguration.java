package cn.ffyzz.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description: Aspect 配置类
 */
public class AspectXMLConfiguration {

    private void beforeAnyPublicMethod() {
        System.out.println("@Before any public method..");
    }

    private Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around any public method.. " + pjp.getSignature());
        return pjp.proceed();
    }

    private void afterReturningAnyPublicMethod() {
        System.out.println("@AfterReturning any public method..");
    }

    private void afterAnyPublicMethod() {
        System.out.println("@After any public method..");
    }

    private void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method..");
    }


}
