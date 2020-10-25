package cn.ffyzz.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class HierarchicalSpringEventpropagationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parentContext");
        parentContext.register(MyListener.class);

        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("currentContext");
        currentContext.register(MyListener.class);

        currentContext.setParent(parentContext);

        parentContext.refresh();

        currentContext.refresh();

        currentContext.close();
        parentContext.close();

    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent> {
        // 去重
        static Set<ApplicationContextEvent> processedEvent = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (processedEvent.add(event)) {
                System.out.printf("监听到 Spring 应用上下文[ ID : %s ] 事件 :%s\n", event.getApplicationContext().getId(),
                        event.getClass().getSimpleName());
            }
        }
    }
}
