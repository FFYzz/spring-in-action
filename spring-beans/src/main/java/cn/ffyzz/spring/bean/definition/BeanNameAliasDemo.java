package cn.ffyzz.spring.bean.definition;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/7
 */
public class BeanNameAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context");
        User aliasUser = beanFactory.getBean("alias-user", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(aliasUser == user);
    }

}
