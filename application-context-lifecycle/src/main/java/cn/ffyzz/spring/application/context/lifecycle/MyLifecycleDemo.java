package cn.ffyzz.spring.application.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/29
 */
public class MyLifecycleDemo {

    public static void main(String[] args) {

        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBeanDefinition("myLifecycle", BeanDefinitionBuilder.rootBeanDefinition(MyLifecycle.class).getBeanDefinition());

        context.refresh();

        context.start();

        context.stop();

        context.close();

    }

}
