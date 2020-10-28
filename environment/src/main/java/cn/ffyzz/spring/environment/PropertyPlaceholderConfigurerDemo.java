package cn.ffyzz.spring.environment;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/27
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/placeholders-resolver.xml");

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        applicationContext.close();
    }

}
