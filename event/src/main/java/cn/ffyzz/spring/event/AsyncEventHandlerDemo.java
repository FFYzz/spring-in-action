package cn.ffyzz.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {

        GenericApplicationContext applicationContext = new GenericApplicationContext();

        // 1.添加自定义 Spring 事件监听器
        applicationContext.addApplicationListener(new MySpringApplicationEventListener());

        // 2.启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 ApplicationEventMulticaster
        ApplicationEventMulticaster applicationEventMulticaster = applicationContext.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);

        // 判断当前 ApplicationEventMulticaster 是否为 SimpleApplicationEventMulticaster
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            // 面向 API 编程，所以需要强转，这是缺陷
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-executor-pool"));
            simpleApplicationEventMulticaster.setTaskExecutor(executorService);

            simpleApplicationEventMulticaster.setErrorHandler((t) -> {
                System.err.println("事件发生异常，异常原因: " + t.getMessage());
            });

            applicationContext.addApplicationListener((ContextClosedEvent event) -> {
                if (!executorService.isShutdown()) {
                    executorService.shutdown();
                }
            });

        }

        applicationContext.addApplicationListener(new ApplicationListener<MySpringApplicationEvent>() {
            @Override
            public void onApplicationEvent(MySpringApplicationEvent event) {
                throw new RuntimeException("故意抛出异常...");
            }
        });

        applicationContext.publishEvent(new MySpringApplicationEvent("Hello, World !!!"));

        applicationContext.close();
    }

}
