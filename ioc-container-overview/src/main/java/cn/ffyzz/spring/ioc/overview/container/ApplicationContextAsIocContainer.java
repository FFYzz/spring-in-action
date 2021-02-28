package cn.ffyzz.spring.ioc.overview.container;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.Import;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/6
 */
//@Import(Temp.class)
public class ApplicationContextAsIocContainer {

    public static void main(String[] args) {
        // 创建一个注解配置的 ApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类注册为配置类
        // 相当于在该类上加了 @Configuration 注解
        applicationContext.register(ApplicationContextAsIocContainer.class);
        ConfigurationClassPostProcessor configurationClassPostProcessor = new ConfigurationClassPostProcessor();
        applicationContext.addBeanFactoryPostProcessor(configurationClassPostProcessor);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找
        lookupCollectionByType(applicationContext);
        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public User getUser() {
        User user = new User();
        user.setName("name");
        user.setId(2L);
        return user;
    }

    @Bean
    public User getAnotherUser() {
        User user = new User();
        user.setName("name222");
        user.setId(3L);
        return user;
    }

    private static void lookupCollectionByType(ApplicationContext applica) {
        if (applica instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) applica;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("lookup Collection by type: ");
            users.values().forEach(System.out::println);
        }
    }

}
