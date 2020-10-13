package cn.ffyzz.spring.dependency.injection;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);

        // 注解的场景也可以使用 xml
        // 读取 bendefinition
        // spring 的应用上下文还是 AnnotationConfigApplicationContext
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(resourcePath);
        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    /**
     * 方法的依赖注入
     * 默认按照类型注入
     *
     * @param user
     * @return
     */
    @Bean
    public UserHolder getUserHolder(User user) {
        // 构造器方式注入
        UserHolder userHolder = new UserHolder(user);
        return userHolder;
    }

}
