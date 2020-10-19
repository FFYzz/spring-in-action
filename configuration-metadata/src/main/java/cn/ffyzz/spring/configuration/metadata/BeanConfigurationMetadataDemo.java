package cn.ffyzz.spring.configuration.metadata;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/18
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        // 创建 BeanDefinitionBuilder
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 获取 BeanDefinition
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        // 设置属性 Attribute
        beanDefinition.setAttribute("name", "Tizs");
        // 设置元数据
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.addPropertyValue("name", "Tomatos");
        // 设置进 BeanDefinition 中
        beanDefinition.setPropertyValues(mutablePropertyValues);
        // 设置 beanDefinition 的来源
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);
        // 创建 IOC 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加后置处理器
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals(beanName, "user") && bean.getClass() == User.class) {
                    BeanDefinition userBeanDefinition = beanFactory.getBeanDefinition("user");
                    System.out.println("Source: " + userBeanDefinition.getSource());
                    User user = (User) bean;
                    user.setName((String) userBeanDefinition.getAttribute("name"));
                }
                return bean;
            }
        });
        // 将 beanDefinition 进行注册
        beanFactory.registerBeanDefinition("user", beanDefinition);
        // 依赖查找
        System.out.println(beanFactory.getBean("user"));

    }

}
