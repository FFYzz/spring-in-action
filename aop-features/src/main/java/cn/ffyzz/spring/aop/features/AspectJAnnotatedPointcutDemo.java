package cn.ffyzz.spring.aop.features;

import cn.ffyzz.spring.aop.features.aspect.AspectConfiguration;
import cn.ffyzz.spring.aop.features.aspect.AspectConfiguration2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Random;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description:
 */
@EnableAspectJAutoProxy
@Configuration
public class AspectJAnnotatedPointcutDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotatedPointcutDemo.class,
                AspectConfiguration.class, AspectConfiguration2.class);
        context.refresh();
        AspectJAnnotatedPointcutDemo demo = context.getBean(AspectJAnnotatedPointcutDemo.class);
        demo.sayHello();
        context.close();
    }

    public void sayHello() {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("For purpose...");
        }
        System.out.println("hello...");
    }

}
