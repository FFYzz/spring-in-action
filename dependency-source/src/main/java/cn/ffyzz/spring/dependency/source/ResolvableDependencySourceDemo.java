package cn.ffyzz.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/12
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);

        // 注册一个回调，在 applicationContext 启动之后，开始注入之前进行注册
        // refresh 中的 invokeBeanFactoryPostProcessors 进行回调
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            if (beanFactory instanceof ConfigurableListableBeanFactory) {
                ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
                configurableListableBeanFactory.registerResolvableDependency(String.class, "Hello ResolvableDependency");
            }
        });

        applicationContext.refresh();
        applicationContext.close();

    }

}
