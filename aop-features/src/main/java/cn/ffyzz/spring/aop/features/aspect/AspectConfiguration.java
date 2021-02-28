package cn.ffyzz.spring.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description: Aspect 配置类
 */
@Aspect
public class AspectConfiguration {

    /**
     * 匹配 join point 的规则定义
     */
    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod() {
    }


    /**
     * 具体的 join point 拦截动作
     */
    @Before("anyPublicMethod()")
    private void beforeAnyPublicMethod() {
        System.out.println("@Before any public method..");
    }

}
