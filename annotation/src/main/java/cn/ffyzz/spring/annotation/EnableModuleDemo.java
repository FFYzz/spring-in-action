package cn.ffyzz.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/27
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(EnableModuleDemo.class);

        applicationContext.refresh();

        String helloWorld = applicationContext.getBean("helloWorld", String.class);

        System.out.println(helloWorld);

        applicationContext.close();
    }

}
