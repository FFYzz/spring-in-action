package cn.ffyzz.spring.bean.definition;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/8
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context");
        User userCreateStaticMethod = beanFactory.getBean("user-create-static-method", User.class);
        User userCreateInstantiationMethod = beanFactory.getBean("user-create-instantiation-method", User.class);
        User userCreateFactoryBean = beanFactory.getBean("user-create-factory-bean", User.class);
        System.out.println(userCreateInstantiationMethod);
        System.out.println(userCreateStaticMethod);
        System.out.println(userCreateFactoryBean);

    }

}
