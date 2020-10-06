package cn.ffyzz.spring.ioc.overview.dependency.injection;

import cn.ffyzz.spring.ioc.overview.annotation.Super;
import cn.ffyzz.spring.ioc.overview.domain.User;
import cn.ffyzz.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/6
 */
public class DependencyInjectionDemo {


    public static void main(String[] args) {
        // 创建上下文
        // 启动 spring 应用上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        UserRepository users = beanFactory.getBean("users", UserRepository.class);
//        System.out.println(users);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖查找，查找错误，不是内建的 bean 对象，而是容器内建依赖
//        System.out.println(beanFactory.getBean(BeanFactory.class));


        // 依赖来源 依赖查找 自定义 Bean
        UserRepository userRepository = beanFactory.getBean("users", UserRepository.class);
        System.out.println(userRepository.getUsers());

        // 依赖来源 依赖注入(容器内建依赖)
        // 是一个 DefaultListableBeanFactory
        System.out.println("userRepository.getBeanFactory(): " + userRepository.getBeanFactory());
        System.out.println("beanFactory: " + beanFactory);
        // 为什么返回 false
        System.out.println("userRepository.getBeanFactory() == beanFactory ? " + (userRepository.getBeanFactory() == beanFactory));

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println("objectFactory.getObject() == applicationContext ? " + (objectFactory.getObject() == applicationContext));

        // true
        System.out.println("classPathXMLApplicationContext.getBeanFactory() == userRepository.getBeanFactory() : " + (beanFactory.getBeanFactory() == userRepository.getBeanFactory()));

        // 依赖来源 容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean：" + environment);

        System.out.println("applicationContext == beanFactory ? " + (applicationContext == beanFactory));

        whoIsIoCContainer(userRepository, applicationContext);
    }

    public static void whoIsIoCContainer(UserRepository userRepository, ApplicationContext applicationContext) {
        System.out.println("userRepository.getBeanFactory() == applicationContext ? " + (userRepository.getBeanFactory() == applicationContext));
    }


}
