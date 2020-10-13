package cn.ffyzz.spring.ioc.bean.scope;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/13
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    // 默认为 singleton
    private User singletonUser() {
        return createUser();
    }


    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private User prototypeUser() {
        return createUser();
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);
        // 无论是 Singleton 还是 Prototype Bean 均会执行初始化方法回调
        // 不过仅 Singleton Bean 会执行销毁方法回调
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s bean: %s 在初始化后回调...\n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });
        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);

        applicationContext.close();

    }

    /**
     * Singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
     * Prototype Bean 无论依赖查找还是依赖注入，均为新生成的对象
     *
     * @param applicationContext
     */
    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("=======================");
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("demo.singletonUser: " + demo.singletonUser);
        System.out.println("demo.singletonUser1: " + demo.singletonUser1);
        System.out.println("demo.prototypeUser: " + demo.prototypeUser);
        System.out.println("demo.prototypeUser1: " + demo.prototypeUser1);
        System.out.println("demo.prototypeUser2: " + demo.prototypeUser2);

        // 如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
        // Prototype Bean 有别于其他地方的依赖注入
        System.out.println("demo.users = " + demo.users);
    }

    /**
     * Singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
     * Prototype Bean 无论依赖查找还是依赖注入，均为新生成的对象
     *
     * @param applicationContext
     */
    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("=======================");
        for (int i = 0; i < 3; ++i) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("singletonUser = " + singletonUser);
            System.out.println("prototypeUser = " + prototypeUser);
        }

    }

    private User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            // 如果当前 Bean 是 prototype scope
            if (beanDefinition.isPrototype()) {
                User user = entry.getValue();
                user.destroy();
            }
        }
        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");
    }

}
