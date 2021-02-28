package cn.ffyzz.spring.bean.lifecycle;

import cn.ffyzz.spring.application.context.lifecycle.CustomizedLifecycle;
import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/17
 */
public class AnnotationBeanInitializationDemo {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeanInitializationDemo.class);
        applicationContext.register(CustomizedLifecycle.class);
        applicationContext.refresh();
        applicationContext.close();

    }

    @Bean(initMethod = "init")
    public UserHolder userHolder() {
        User user = new User();
        return new UserHolder(user);
    }

}
