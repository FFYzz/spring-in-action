package cn.ffyzz.spring.configuration.metadata;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/18
 */
public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("META-INF/user-bean-definitions.properties");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int count = reader.loadBeanDefinitions(encodedResource);
        System.out.println("number of beans = " + count);
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

}
