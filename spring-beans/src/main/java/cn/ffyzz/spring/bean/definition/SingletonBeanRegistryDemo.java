package cn.ffyzz.spring.bean.definition;

import cn.ffyzz.spring.bean.factory.DefaultUserFactory;
import cn.ffyzz.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/12
 */
public class SingletonBeanRegistryDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        singletonBeanRegistry.registerSingleton("userFactory", userFactory);
        applicationContext.refresh();
        UserFactory lookupUserFactory = applicationContext.getBean(userFactory.getClass());
        System.out.println("userFactory == lookupUserFactory ? " + (userFactory == lookupUserFactory));
        applicationContext.close();

    }

}
