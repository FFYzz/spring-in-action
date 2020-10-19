package cn.ffyzz.spring.configuration.metadata;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/19
 */
public class XmlBasedYamlPropertySourceDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int count = reader.loadBeanDefinitions("classpath:/META-INF/yaml-property-source-context.xml");
        System.out.println("loaded BeanDefinition Count: " + count);

        Map<String, Object> map = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(map);

    }

}
