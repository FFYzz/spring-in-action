package cn.ffyzz.spring.configuration.metadata;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/19
 */
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
//@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
//@PropertySource("classpath:/META-INF/user-bean-definitions2.properties")
@PropertySources({@PropertySource("classpath:/META-INF/user-bean-definitions.properties"),
        @PropertySource("classpath:/META-INF/user-bean-definitions2.properties")})
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        applicationContext.refresh();

        Map<String, User> users = applicationContext.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : users.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        applicationContext.close();
    }

    /**
     * 输出的 name 是 jasonchen 而不是配置文件中定义的 Tizs，存在一个优先级问题，后面分析
     *
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User configuedUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    @Bean
    public User configuedUser2(@Value("${user2.id}") Long id, @Value("${user2.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

}
