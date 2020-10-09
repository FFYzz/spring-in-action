package cn.ffyzz.spring.bean.definition;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/7
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        // 0. 通过 BeanDefinitionRegistry 来注册 bean，并实例化
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();

        // 1. 通过 BeanDefinitionBuilder 创建 BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性
        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "Jack");
        // 得到 BeanDefinition，此时的 BeanDefinition 并不是最终态，还可以进行设置属性
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
//        beanDefinition.setLazyInit(true);

        // 2. 通过 AbstractBeanDefinition 及其子类创建 BeanDefinition
        AbstractBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues
                .add("id", 2L)
                .add("name", "Mike");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

        // 4. 通过 AbstractBeanDefinition 的 setInitMethod 设置初始化方法 [37]
        beanDefinition.setInitMethodName("initMethodByJavaAPI");

        // 3. 实例化 bean
        // 3.1 注册 BeanDefinition
        genericApplicationContext.registerBeanDefinition("user from BeanDefinitonRegistry", beanDefinition);
        // 3.2 启动应用上下文
        genericApplicationContext.refresh();
        // 3.3 获取 bean 实例
        User user = genericApplicationContext.getBean("user from BeanDefinitonRegistry", User.class);


        System.out.println(user);


    }

}