package cn.ffyzz.spring.dependency.injection;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    UserHolder userHolder;

    /**
     * JDK 11 中已经移除
     * 需要额外添加依赖
     * 可以多次注入
     */
    @Resource
    UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        // 注解的场景也可以使用 xml
        // 读取 bendefinition
        // spring 的应用上下文还是 AnnotationConfigApplicationContext
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(resourcePath);
        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        UserHolder userHolder1 = demo.userHolder;
        UserHolder userHolder2 = demo.userHolder2;
        System.out.println(userHolder1);
        System.out.println(userHolder2);
        System.out.println(userHolder1 == userHolder2);

        applicationContext.close();
    }

    /**
     * 方法的依赖注入
     *
     * @param user
     * @return
     */
    @Bean
    public UserHolder getUserHolder(User user) {
        // setter 方式注入
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

}
