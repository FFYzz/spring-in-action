package cn.ffyzz.spring.bean.definition;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/7
 */
// Import 注解生效的前提是 AnnotationBeanDefinitionDemo 被注册到 IOC 容器中
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        registerBeanDefinitionWithAnnotation(applicationContext);

        registerBeanDefinitionWithJavaAPI(applicationContext);

        // 启动 spring 应用上下文
        applicationContext.refresh();

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println("===========");
        for (String str : beanDefinitionNames) {
            System.out.println(str);
        }
        System.out.println("===========");

        Map<String, User> beansOfUser = applicationContext.getBeansOfType(User.class);
        Map<String, Config> beansOfConfig = applicationContext.getBeansOfType(Config.class);

        System.out.println("beansOfUser: " + beansOfUser);
        System.out.println("beansOfConfig: " + beansOfConfig);

        // 手动关闭 spring 应用上下文
        applicationContext.close();
    }

    private static void registerBeanDefinitionWithAnnotation(AnnotationConfigApplicationContext applicationContext) {
        // 将当前类注册类配置类
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        applicationContext.register(Config.class);
    }

    private static void registerBeanDefinitionWithJavaAPI(AnnotationConfigApplicationContext applicationContext) {
        // 通过 BeanDefinition API 对 bean 进行注册
        // 带 bean name
        registerUserBeanDefinition(applicationContext, "bean-name-user");
        // 不带 bean name，调用生成 name API
        registerUserBeanDefinition(applicationContext);
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 2L)
                .addPropertyValue("name", "name-2");
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    /**
     * 通过 @Component 方式，将当前类注册为配置类
     * 无法注册 TODO
     */
    @Component
    public static class Config {
        /**
         * 1. 通过 @Bean 方式
         *
         * @return
         */
        @Bean(name = "annotation-user-1")
        public User getUser() {
            User user = new User();
            user.setId(100L);
            user.setName("Mike");
            return user;
        }

        @Override
        public String toString() {
            return "Config{}";
        }
    }

}
