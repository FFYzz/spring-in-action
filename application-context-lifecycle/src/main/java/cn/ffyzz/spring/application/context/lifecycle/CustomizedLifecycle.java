package cn.ffyzz.spring.application.context.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/29
 */
public class CustomizedLifecycle implements Lifecycle {

    private boolean running = false;

    @Override
    public void start() {
        running = true;
        System.out.println("CustomizedLifecycle 启动中....");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("CustomizedLifecycle 关闭中....");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

}
