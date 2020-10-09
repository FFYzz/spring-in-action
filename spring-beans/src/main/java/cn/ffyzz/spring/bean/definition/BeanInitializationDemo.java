package cn.ffyzz.spring.bean.definition;

import cn.ffyzz.spring.bean.factory.DefaultUserFactory;
import cn.ffyzz.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/9
 */
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类注册为 bean
        applicationContext.register(BeanInitializationDemo.class);
        // 启动应用上下文
        System.out.println("spring 应用上下文准备启动");
        applicationContext.refresh();
        System.out.println("spring 应用上下文已经启动");
        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("spring 应用上下文准备关闭");
        // 关闭应用上下文
        applicationContext.close();
        System.out.println("spring 应用上下文已经关闭");
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyBean")
    @Lazy(value = false)
    public UserFactory getUserFactory() {
        return new DefaultUserFactory();
    }

}
