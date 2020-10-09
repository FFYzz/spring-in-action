package cn.ffyzz.spring.bean.factory;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/8
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    private String msg;

    @Override
    public User createUser() {
        User user = new User();
        user.setName("instantiation user");
        user.setId(90L);
        return user;
    }

    // PostConstruct 在 java9 中废弃，在 Java 11 中被移除
    // 所以使用该注解需要添加 maven 依赖
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: init");
        msg = "@PostConstruct msg";
    }


    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet: init");
        msg = "afterPropertiesSet msg";
    }

    /**
     * 自定义初始化方法
     */
    public void initMethod() {
        System.out.println("initMethod: init");
        msg = "initMethod msg";
    }

    @PreDestroy
    public void destroyWithAnnotation() {
        System.out.println("@PreDestroy: destroy");
    }

    /**
     * 自定义销毁 bean 方法
     */
    public void destroyBean() {
        System.out.println("destroyBean: destroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean: destroy");
    }

    @Override
    public String toString() {
        return "DefaultUserFactory{" +
                "msg='" + msg + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("bean finalize...");
    }
}
