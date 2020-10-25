package cn.ffyzz.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class CustomizedSpringEventDemo {

    public static void main(String[] args) {

        GenericApplicationContext applicationContext = new GenericApplicationContext();

        applicationContext.addApplicationListener(new MySpringApplicationEventListener());
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("event: " + event);
            }
        });


        applicationContext.refresh();

        applicationContext.publishEvent(new MySpringApplicationEvent("Hello, World AGAIN!"));

        applicationContext.publishEvent(new MySpringApplicationEvent2("Hello, World 22222!"));

        applicationContext.close();

    }

}
