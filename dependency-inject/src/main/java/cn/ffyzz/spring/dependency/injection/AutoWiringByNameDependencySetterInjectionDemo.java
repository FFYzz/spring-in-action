package cn.ffyzz.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class AutoWiringByNameDependencySetterInjectionDemo {

    public static void main(String[] args) {
        // 创建 beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // xml reader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 加载资源
        String resourcePath = "classpath:/autowiring-by-name-setter-dependency-injection-context.xml";
        reader.loadBeanDefinitions(resourcePath);
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }

}
