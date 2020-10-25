package cn.ffyzz.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class MySpringApplicationEvent2 extends MySpringApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringApplicationEvent2(Object source) {
        super(source);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    @Override
    public String toString() {
        return getSource();
    }
}
