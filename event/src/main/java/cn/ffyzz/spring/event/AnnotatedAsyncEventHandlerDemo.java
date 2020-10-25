package cn.ffyzz.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 1. 注册当前类作为 Configuration Class
        applicationContext.register(AnnotatedAsyncEventHandlerDemo.class);

        // 2.启动 Spring 应用上下文
        applicationContext.refresh();

        applicationContext.publishEvent(new MySpringApplicationEvent("hELLO, wORLD !!!"));

        applicationContext.close();
    }

    @EventListener
    @Async
    public void onEvent(MySpringApplicationEvent event) {
        System.out.printf("[线程]: %s, onEvent -> MySpringApplicationEvent 接收到事件: %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public Executor taskExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-executor-pool-aaa"));
        return executorService;
    }

}
