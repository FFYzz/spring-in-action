package cn.ffyzz.spring.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/27
 */
@Configuration // 可加可不加
public class ProfileDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(ProfileDemo.class);

        ConfigurableEnvironment environment = context.getEnvironment();
        // 设置默认的 profile 是一个兜底策略
//        environment.setDefaultProfiles("odd");

        // 设置激活的 Profile
        environment.setActiveProfiles("even");

        // 启动 Spring 应用上下文
        context.refresh();

        Integer number = context.getBean("number", Integer.class);

        System.out.println(number);

        // 关闭 Spring 应用上下文
        context.close();

    }

    @Bean(name = "number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
    @Conditional(EvenConditional.class)
    public Integer even() {
        return 2;
    }

}
