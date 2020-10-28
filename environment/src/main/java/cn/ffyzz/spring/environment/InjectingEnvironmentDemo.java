package cn.ffyzz.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/28
 */
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;

    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment2;

    @Autowired
    private ApplicationContext applicationContext2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingEnvironmentDemo.class);

        applicationContext.refresh();

        InjectingEnvironmentDemo demo = applicationContext.getBean(InjectingEnvironmentDemo.class);

        System.out.println(demo.environment == demo.environment2);
        System.out.println(demo.applicationContext == demo.applicationContext2);
        System.out.println(demo.applicationContext.getEnvironment() == demo.environment);


        applicationContext.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
