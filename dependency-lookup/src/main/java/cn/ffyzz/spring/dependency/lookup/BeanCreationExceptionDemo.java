package cn.ffyzz.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Clazz.class);
        try {
            applicationContext.registerBeanDefinition("errorBean", builder.getBeanDefinition());
        } catch (BeanInstantiationException e) {
            e.printStackTrace();
        }

        applicationContext.refresh();

        applicationContext.close();
    }

    static class Clazz implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("throw Exception....");
        }
    }

}
