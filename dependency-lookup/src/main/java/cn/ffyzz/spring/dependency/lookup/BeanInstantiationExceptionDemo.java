package cn.ffyzz.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        try {
            applicationContext.registerBeanDefinition("errorBean", builder.getBeanDefinition());
        } catch (BeanInstantiationException e) {
            e.printStackTrace();
        }

        applicationContext.refresh();

        applicationContext.close();
    }

}
