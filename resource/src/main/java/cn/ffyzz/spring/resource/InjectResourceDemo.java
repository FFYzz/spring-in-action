package cn.ffyzz.spring.resource;

import cn.ffyzz.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/20
 */
public class InjectResourceDemo {

    @Value("classpath:/default.properties")
    Resource resource;

    @Value("classpath:/*.properties")
    Resource[] resources;

    @Value("${user.dir}")
    String currentProjectPath;


    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(resource));
        System.out.println("=================");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("=================");
        System.out.println(currentProjectPath);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectResourceDemo.class);

        applicationContext.refresh();
        applicationContext.close();
    }

}
