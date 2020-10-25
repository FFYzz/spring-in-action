package cn.ffyzz.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
//        GenericApplicationContext applicationContext = new GenericApplicationContext();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationListenerDemo.class);
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("[onApplicationEvent] 接收到事件: " + event);
            }
        });
        applicationContext.refresh();
        applicationContext.close();
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("[@EventListener ContextRefreshedEvent] 接收到事件: " + event);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("[@EventListener ContextClosedEvent] 接收到事件: " + event);
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello, World") {
        });
        // 发送 PayloadApplicationEvent 事件
        applicationEventPublisher.publishEvent("Hello, World");
    }
}
