package cn.ffyzz.spring.bean.definition;

import cn.ffyzz.spring.bean.factory.DefaultUserFactory;
import cn.ffyzz.spring.bean.factory.UserFactory;
import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/8
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context");

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        // 通过 ServiceLoaderFactoryBean 实例化 userFactory
        // 然后通过 userFactory 来实例化 User

        // 1. Spring 中的 serviceLoaderFactoryBean
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        // 可以获取单个 userFactory
        displayServiceLoader(serviceLoader);
        System.out.println("==end userFactoryServiceLoader==");

        // 2. Spring 中的 ServiceFactoryBean [类似]
        // ServiceFactoryBean 返回 UserFactory，而不是 ServiceLoader
        UserFactory userFactory = beanFactory.getBean("userFactoryService", UserFactory.class);
        System.out.println(userFactory.createUser());
        System.out.println("==end userFactoryService==");

        // 3. Spring 中的 ServiceListFactoryBean [类似]
        List<UserFactory> userFactories = beanFactory.getBean("userFactoryServiceList", List.class);
        for (UserFactory uf : userFactories) {
            System.out.println(uf.createUser());
        }
        System.out.println("==end userFactoryServiceList==");

        // 4. Java 原生的 ServiceLoader[类似]
        serviceLoaderTest();
        System.out.println("==end Java ServiceLoader==");

        // 通过 AutowireCapableBeanFactory 创建 userFactory

        // 1. 和 ServiceLoader 没有关系了
        // 注意点: Class 类型必须为具体的类型
        UserFactory autowireCapableBeanFactoryGeneratedUserFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(autowireCapableBeanFactoryGeneratedUserFactory.createUser());
        System.out.println("==end autowireCapableBeanFactoryGeneratedUserFactory==");

        // 通过 BeanDefinitionRegistry#registryBeanDefinition 实例化 bean

        // 1. 通过 BeanDefinitionBuilder 创建 BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 2. 为 beanDefinition 添加属性
        beanDefinitionBuilder
                .addPropertyValue("id", 203L)
                .addPropertyValue("name", "user from beanDefinition");
        beanDefinitionBuilder.getBeanDefinition();
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        // 需要一个实现了 BeanDefinitionRegistry 接口的 ApplicationContext 来进行注册
        // 这里的 ClassPathXmlApplicationContext 没有匹配的实现了 BeanDefinitionRegistry 的父类，所以没法往下写了
        // 可以参看 BeanDefinitionCreationDemo 中的 3
    }

    // Java 原生的 ServiceLoader 从 META-INF 的 services 下加载接口的实现类
    public static void serviceLoaderTest() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class);
        displayServiceLoader(serviceLoader);
    }

    public static void displayServiceLoader(ServiceLoader serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            User user = userFactory.createUser();
            System.out.println(user);
        }
    }

}
