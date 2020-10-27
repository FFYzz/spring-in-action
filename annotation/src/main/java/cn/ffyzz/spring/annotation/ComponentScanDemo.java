package cn.ffyzz.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/26
 */
//@ComponentScan(basePackages = "cn.ffyzz.spring.annotation")
//    @MyComponentScan(scanBasePackages = "cn.ffyzz.spring.annotation")
    @MyComponentScan2(packages = {"cn.ffyzz.spring.annotation"})
public class ComponentScanDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ComponentScanDemo.class);

        applicationContext.refresh();

        TestClass testClass = applicationContext.getBean(TestClass.class);
        System.out.println(testClass);

        applicationContext.close();
    }

}
