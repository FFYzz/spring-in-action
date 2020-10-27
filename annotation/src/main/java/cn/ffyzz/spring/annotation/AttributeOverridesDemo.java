package cn.ffyzz.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/27
 */

@MyComponentScan2(scanBasePackages = {"cn.ffyzz.spring.annotation"})
public class AttributeOverridesDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AttributeOverridesDemo.class);

        applicationContext.refresh();

        TestClass testClass = applicationContext.getBean(TestClass.class);
        System.out.println(testClass);

        applicationContext.close();
    }

}
