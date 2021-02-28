package cn.ffyzz.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description:
 */
@EnableAspectJAutoProxy
@Aspect
@Configuration
public class AspectJAnnotationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotationDemo.class);
        context.refresh();
        AspectJAnnotationDemo demo = context.getBean(AspectJAnnotationDemo.class);
        context.close();

    }

}
