package cn.ffyzz.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/28
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String name;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ValueAnnotationDemo.class);

        applicationContext.refresh();

        ValueAnnotationDemo demo = applicationContext.getBean(ValueAnnotationDemo.class);

        System.out.println(demo.name);

        applicationContext.close();
    }

}
