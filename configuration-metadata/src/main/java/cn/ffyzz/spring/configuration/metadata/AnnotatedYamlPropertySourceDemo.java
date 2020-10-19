package cn.ffyzz.spring.configuration.metadata;

import cn.ffyzz.spring.ioc.overview.domain.User;
import cn.ffyzz.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/19
 */
@PropertySource(
        name = "yamlPropertiesSource",
        value = "classpath:/META-INF/user.yaml",
        factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedYamlPropertySourceDemo.class);
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        applicationContext.close();

    }

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

}
