package cn.ffyzz.spring.environment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/28
 */
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LookupEnvironmentDemo.class);

        applicationContext.refresh();

        Environment lookupEnvironment = applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);

        LookupEnvironmentDemo demo = applicationContext.getBean(LookupEnvironmentDemo.class);

        System.out.println(demo.environment == lookupEnvironment);

        applicationContext.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
