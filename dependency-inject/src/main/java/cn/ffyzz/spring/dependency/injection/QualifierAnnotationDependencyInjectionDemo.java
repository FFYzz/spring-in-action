package cn.ffyzz.spring.dependency.injection;

import cn.ffyzz.spring.dependency.injection.annotation.GroupUser;
import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user1;

    @Autowired
    @Qualifier("user")
    private User user2;

    @Bean
    private User user3() {
        return createUser(100L);
    }

    @Bean
    @Qualifier
    private User user4() {
        return createUser(4l);
    }

    @Bean
    @Qualifier
    private User user5() {
        return createUser(5l);
    }

    @Bean
    @GroupUser
    private User user6() {
        return createUser(6l);
    }

    @Bean
    @GroupUser
    private User user7() {
        return createUser(7l);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @GroupUser
    private Collection<User> groupUsers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        // 注解的场景也可以使用 xml
        // 读取 bendefinition
        // spring 的应用上下文还是 AnnotationConfigApplicationContext
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(resourcePath);
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user1);
        System.out.println(demo.user2);
        System.out.println(demo.user3());
        System.out.println(demo.user4());
        System.out.println(demo.user5());
        System.out.println(demo.allUsers);
        System.out.println(demo.qualifierUsers);
        System.out.println(demo.groupUsers);

        applicationContext.close();
    }

}
