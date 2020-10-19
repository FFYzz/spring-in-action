package cn.ffyzz.spring.configuration.metadata;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/19
 */
@org.springframework.context.annotation.PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class PropertySourceDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(PropertySourceDemo.class);

        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();

        Map<String, Object> map = new HashMap<>();

        map.put("user.name", "kisy");

        PropertySource firstPropertySource = new MapPropertySource("self-added-property-source", map);

        propertySources.addFirst(firstPropertySource);

        context.refresh();

        for (PropertySource propertySource : propertySources) {
            System.out.println(propertySource);
        }

        Map<String, User> user = context.getBeansOfType(User.class);

        user.forEach((key, value) -> {
            System.out.println("key: " + key + ", value: " + value);
        });

        context.close();

    }

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

}
