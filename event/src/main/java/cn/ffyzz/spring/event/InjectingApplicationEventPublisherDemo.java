package cn.ffyzz.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware,
        ApplicationContextAware {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingApplicationEventPublisherDemo.class);
        applicationContext.addApplicationListener(new MySpringApplicationEventListener());
        applicationContext.refresh();

        applicationContext.close();
    }

    @PostConstruct
    public void init() {
        applicationEventPublisher.publishEvent(new MySpringApplicationEvent("The event from @Autowired ApplicationEventPublisher"));
        applicationContext.publishEvent(new MySpringApplicationEvent("The event from @Autowired ApplicationContext"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new MySpringApplicationEvent("The event from ApplicationEventPublisherAware"));
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationEventPublisher.publishEvent(new MySpringApplicationEvent("The event from ApplicationContextAware"));

    }
}
