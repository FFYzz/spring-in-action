package cn.ffyzz.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class MySpringApplicationEventListener implements ApplicationListener<MySpringApplicationEvent> {

    @Override
    public void onApplicationEvent(MySpringApplicationEvent event) {
        System.out.printf("[线程]: %s, MySpringApplicationEventListener 接收到事件: %s\n", Thread.currentThread().getName(), event);
    }

}
