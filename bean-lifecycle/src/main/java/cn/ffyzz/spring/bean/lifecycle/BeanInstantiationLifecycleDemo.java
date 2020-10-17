package cn.ffyzz.spring.bean.lifecycle;

import cn.ffyzz.spring.ioc.overview.domain.SuperUser;
import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/14
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
//        executeBeanFactory();
//        System.out.println("-------------------");
        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 方式一 通过添加 BeanPostProcessor
//        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        String[] locations = {"classpath:/spring-bean-lifecycle-context.xml", "classpath:/bean-constructor-dependency-injection.xml"};
        // 方式二 通过将 MyInstantiationAwareBeanPostProcessor 在当前上下文中注册为 bean
        // 在当前方法中就失效了
        // TODO 具体原因还不明 --明了，没注册到 BeanFactory 中
        // wrong 因为 loadBeanDefinition 并没有将 bean 进行实例化，只是储存了 BeanDefinition 的原信息
        // wrong 所以需要手动去 getBean 一次,以触发其实例化以及初始化
        // 因为没有注册 BeanPostProcessor
        int count = reader.loadBeanDefinitions(locations);
        System.out.println("bean 的数量: " + count);
        // 上下文中早已经都有了 user Bean 和 superuser bean
        // 手动去 getBean 也是无效
//        beanFactory.getBean("myInstantiationAwareBeanPostProcessor");
        // 需要将其注册进去
        beanFactory.addBeanPostProcessor((BeanPostProcessor) beanFactory.getBean("myInstantiationAwareBeanPostProcessor"));
        SuperUser superuser = beanFactory.getBean("superuser", SuperUser.class);
        System.out.println(superuser);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
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
