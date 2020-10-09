package cn.ffyzz.spring.bean.definition;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/7
 */
public class BeanNameDemo {

    public static void main(String[] args) {
        // 不同的 IOC 容器可以有相同的 bean identifier
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context");
        User user = applicationContext.getBean("user5", User.class);
        System.out.println(user);
        ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context2");
        User user2 = applicationContext2.getBean("user5", User.class);
        System.out.println(user2);
    }

}
