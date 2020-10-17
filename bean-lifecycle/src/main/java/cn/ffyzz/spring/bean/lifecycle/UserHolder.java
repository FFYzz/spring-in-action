package cn.ffyzz.spring.bean.lifecycle;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/16
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        EnvironmentAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

    private final User user;

    private Long number;

    private String descriptor;

    private String beanName;

    private BeanFactory beanFactory;

    private ClassLoader beanClassLoader;

    private Environment environment;

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * 需要开启注解驱动
     */
    @PostConstruct
    public void postConstructInit() {
        this.descriptor = "the user holder descriptor V6";
        System.out.println("postConstructInit: " + descriptor);
    }

    @Override
    public void afterPropertiesSet() {
        this.descriptor = "the user holder descriptor V7";
        System.out.println("afterPropertiesSet: " + descriptor);
    }

    public void init() {
        this.descriptor = "the user holder descriptor V8";
        System.out.println("init: " + descriptor);
    }


    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", descriptor='" + descriptor + '\'' +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.descriptor = "The user holder V10";
        System.out.println("afterSingletonsInstantiated: " + descriptor);
    }

    @PreDestroy
    public void preDesrtoy() {
        this.descriptor = "The user holder V12";
        System.out.println("preDesrtoy: " + descriptor);
    }

    @Override
    public void destroy() {
        this.descriptor = "The user holder V13";
        System.out.println("destroy: " + descriptor);
    }

    public void selfDefDestroy() {
        this.descriptor = "The user holder V14";
        System.out.println("selfDefDestroy: " + descriptor);
    }
}
