package cn.ffyzz.spring.ioc.overview.dependency.lookup;

import cn.ffyzz.spring.ioc.overview.annotation.Super;
import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/6
 */
public class DependencyLookupDemo {


    public static void main(String[] args) {
        // 创建上下文
        // 启动 spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);
        lookupByAnnotation(beanFactory);

    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> superUsers = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("lookup with annotation: " + superUsers);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("lookup Collection by type: " + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        // 该类型的 bean 定义只能为一个
        User bean = beanFactory.getBean(User.class);
        System.out.println("lookup by type: " + bean);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        // 延迟查找 需要借助 ObjectFactoryCreatingFactoryBean
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟加载: " + user);
    }

    public static void lookupInRealTime(BeanFactory beanFactory) {
        // 实时查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println("实时加载: " + user);
    }


}
