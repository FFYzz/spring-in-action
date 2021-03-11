package cn.ffyzz.spring.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description: Aspect 配置类
 */
@Aspect
public class AspectConfiguration2 implements Ordered {

    /**
     * 具体的 join point 拦截动作
     */
    @Before("execution(public * *(..))")
    private void beforeAnyPublicMethod() {
        System.out.println("@Before any public method2222..");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
