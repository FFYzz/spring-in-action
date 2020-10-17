package cn.ffyzz.spring.bean.lifecycle;

import cn.ffyzz.spring.ioc.overview.domain.SuperUser;
import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/17
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
//        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 方式一 通过添加 BeanPostProcessor
//        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        String[] locations = {"classpath:/spring-bean-lifecycle-context.xml", "classpath:/bean-constructor-dependency-injection.xml"};
        // 方式二 通过将 MyInstantiationAwareBeanPostProcessor 在当前上下文中注册为 bean
        int count = reader.loadBeanDefinitions(locations);
        System.out.println("bean 的数量: " + count);
        // 上下文中早已经都有了 user Bean 和 superuser bean
        // 手动去 getBean 也是无效
        // 这里两个 BeanPostProcessor 的添加顺序很有讲究，先添加的会先执行
//        beanFactory.getBean("myInstantiationAwareBeanPostProcessor");
        // 需要将其注册进去
        beanFactory.addBeanPostProcessor((BeanPostProcessor) beanFactory.getBean("myInstantiationAwareBeanPostProcessor"));
        // 开启注解驱动，使得 @PostConstruct 注解生效
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        // BeanFactory 没有能力去执行 ApplicationContextAwareProcessor 的 Aware 能力
//        beanFactory.addBeanPostProcessor();
        beanFactory.preInstantiateSingletons();
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
        SuperUser superuser = beanFactory.getBean("superuser", SuperUser.class);
        System.out.println(superuser);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations = {"classpath:/spring-bean-lifecycle-context.xml", "classpath:/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        // 上下文中早已经都有了 user Bean 和 superuser bean
        applicationContext.refresh();
        System.out.println(applicationContext.getBeanDefinitionCount());
        SuperUser superuser = applicationContext.getBean("superuser", SuperUser.class);
        System.out.println(superuser);
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();

    }




}
