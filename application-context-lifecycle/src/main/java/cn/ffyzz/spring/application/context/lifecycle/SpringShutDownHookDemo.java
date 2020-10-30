package cn.ffyzz.spring.application.context.lifecycle;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/29
 */
public class SpringShutDownHookDemo {

    public static void main(String[] args) throws IOException {

        GenericApplicationContext context = new GenericApplicationContext();

        context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.printf("线程 [%s] close application context", Thread.currentThread().getName());
            }
        });

        context.registerShutdownHook();

        context.refresh();

        System.in.read();

        context.close();

    }

}
