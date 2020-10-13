package cn.ffyzz.spring.dependency.lookup;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/10
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        // 启动容器
        applicationContext.refresh();

        // 演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        // 演示 ObjectProvider#getIfAvaiable 方法的安全性
        displayObjectProviderIfAvailable(applicationContext);

        // 演示 ListableBeanFactory#getBeansOfType 方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider Stream 操作的安全性
        displayObjectProviderStreamOps(applicationContext);

        // 关闭容器
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectProviderStreamOps", () -> {
            userObjectProvider.forEach(System.out::println);
        });
    }

    private static void displayListableBeanFactoryGetBeansOfType(AnnotationConfigApplicationContext applicationContext) {
        printBeanException("displayListableBeanFactoryGetBeansOfType", () -> {
            applicationContext.getBeansOfType(User.class);
        });
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectProviderIfAvailable", () -> {
            userObjectProvider.getIfAvailable(User::createUser);
        });
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectFactoryGetObject", () -> {
            objectFactory.getObject();
        });
    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        printBeanException("displayBeanFactoryGetBean", () -> {
            applicationContext.getBean(User.class);
        });
    }

    private static void printBeanException(String source, Runnable runnable) {
        System.err.println("--------------");
        System.err.println("source from: " + source);
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
