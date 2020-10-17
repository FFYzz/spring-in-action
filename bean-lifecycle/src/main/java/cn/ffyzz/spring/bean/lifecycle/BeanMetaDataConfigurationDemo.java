package cn.ffyzz.spring.bean.lifecycle;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/14
 */
public class BeanMetaDataConfigurationDemo {

    public static void main(String[] args) {
        loadBeanDefinitionByPropertiesFile();
        loadBeanDefinitionByXMLFile();
    }

    private static void loadBeanDefinitionByPropertiesFile() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        System.out.println("load by properties:");
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        String configFilePath = "user.properties";
        Resource configResource = new ClassPathResource(configFilePath);
        // EncodedResource 可以解决中文乱码的问题，因为默认为 ASCII 编码读取，而配置文件中文本的编码为 UTF-8
        EncodedResource encodedResource = new EncodedResource(configResource, "UTF-8");
        int count = reader.loadBeanDefinitions(encodedResource);
        System.out.println("bean 的个数为: " + count);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

    private static void loadBeanDefinitionByXMLFile() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        System.out.println("load by xml:");
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String configFilePath = "classpath:/user.xml";
        int count = reader.loadBeanDefinitions(configFilePath);
        System.out.println("bean 的个数为: " + count);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
