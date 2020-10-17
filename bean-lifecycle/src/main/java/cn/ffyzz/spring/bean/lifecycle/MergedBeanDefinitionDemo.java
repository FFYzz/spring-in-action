package cn.ffyzz.spring.bean.lifecycle;

import cn.ffyzz.spring.ioc.overview.domain.SuperUser;
import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/14
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/spring-bean-lifecycle-context.xml";
        int count = reader.loadBeanDefinitions(location);
        System.out.println("bean 的数量: " + count);
        // 上下文中早已经都有了 user Bean 和 superuser bean
        SuperUser superuser = beanFactory.getBean("superuser", SuperUser.class);
        System.out.println(superuser);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

}
