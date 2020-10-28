package cn.ffyzz.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/28
 */
public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}")
    private String name;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnvironmentPropertySourceChangeDemo.class);

        // spring context 启动前修改 PropertySource
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String, Object> map = new HashMap<>();
        MapPropertySource mapPropertySource = new MapPropertySource("mapPropertySource", map);
        propertySources.addFirst(mapPropertySource);
        map.put("user.name", "Tizs");

        applicationContext.refresh();

        // spring context 启动后修改 PropertySource

        // 没有动态修改的能力
        map.put("user.name", "Tizs01");

        EnvironmentPropertySourceChangeDemo demo = applicationContext.getBean(EnvironmentPropertySourceChangeDemo.class);

        for (PropertySource propertySource : propertySources) {
            System.out.println("name = " + propertySource.getName() + ", source = " + propertySource.getProperty("user.name"));
        }
        System.out.println(demo.name);

        applicationContext.close();

    }

}
