package cn.ffyzz.spring.application.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/29
 */
public class ApplicationContextLifecycleDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBeanDefinition("customizedLifecycle1", BeanDefinitionBuilder.rootBeanDefinition(CustomizedLifecycle.class).getBeanDefinition());
        context.refresh();
        context.start();
        context.stop();
        context.close();

    }

}
