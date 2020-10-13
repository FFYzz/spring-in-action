package cn.ffyzz.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/12
 */
public class DependencySourceDemo {

    // Autowired 的注入在 postProcessProperties 方法执行，会早于 setter 注入，也会早于 @PostConstruct 初始化
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * 依赖查找
     * 下面四个查找报错，因为 Spring 的依赖查找来源不支持 ResolvableDependency 类型
     */
    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    /**
     * 依赖注入
     */
    @PostConstruct
    public void initByInject() {
        System.out.println("beanFactory == applicationContext: " + (beanFactory == applicationContext));
        System.out.println("beanFactory == resourceLoader: " + (beanFactory == resourceLoader));
        System.out.println("beanFactory == eventPublisher: " + (beanFactory == eventPublisher));
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory(): " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
    }

    public <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型 " + beanType.getName() + " 无法在 BeanFactory 中查找!");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        applicationContext.refresh();

        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);


        applicationContext.close();

    }

}
