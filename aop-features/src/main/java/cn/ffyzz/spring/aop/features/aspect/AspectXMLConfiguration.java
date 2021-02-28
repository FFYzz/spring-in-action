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
public class AspectXMLConfiguration {

    private void beforeAnyPublicMethod() {
        System.out.println("@Before any public method..");
    }

}
