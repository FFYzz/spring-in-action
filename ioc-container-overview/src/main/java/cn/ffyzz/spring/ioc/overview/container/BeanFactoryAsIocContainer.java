package cn.ffyzz.spring.ioc.overview.container;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/6
 */
public class BeanFactoryAsIocContainer {

    public static void main(String[] args) {
        // 创建一个 BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // 创建一个 Reader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resourcePath = "classpath:/META-INF/dependency-injection-context.xml";
        // 加载配置，返回读取到的 bean 的个数
        int beanDefinitionCount = reader.loadBeanDefinitions(resourcePath);
        System.out.println("读取到的 bean 的个数: " + beanDefinitionCount);
        // 依赖查找
        lookupCollectionByType(defaultListableBeanFactory);

        // 这里应该会抛出异常，因为又不止一个 User 类型的 bean
        BeanFactoryUtils.beanOfType(defaultListableBeanFactory, User.class);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("lookup Collection by type: " + users);
        }
    }

}
