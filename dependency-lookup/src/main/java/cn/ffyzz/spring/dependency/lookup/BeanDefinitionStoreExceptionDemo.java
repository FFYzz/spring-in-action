package cn.ffyzz.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class BeanDefinitionStoreExceptionDemo {

    public static void main(String[] args) {

        // doesnot work
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/forbidden-read-context.xml");

    }

}
