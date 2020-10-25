package cn.ffyzz.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable eventObservable = new EventObservable();
        EventObserver eventObserver = new EventObserver("o1");
        EventObserver eventObserver1 = new EventObserver("o2");
        EventObserver eventObserver2 = new EventObserver("o3");
        EventObserver eventObserver3 = new EventObserver("o4");

        eventObservable.addObserver(eventObserver3);
        eventObservable.addObserver(eventObserver);
        eventObservable.addObserver(eventObserver1);
        eventObservable.addObserver(eventObserver2);

        eventObservable.notifyObservers("Hello Fresh");
    }

    /**
     * 一对多中的 一
     */
    static class EventObserver implements Observer, EventListener {
        private String observerName;

        public EventObserver(String observerName) {
            this.observerName = observerName;
        }

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = new EventObject(event);
            System.out.printf("观察者: %s, 收到事件: %s\n", observerName, eventObject);
        }

        @Override
        public String toString() {
            return "EventObserver{" +
                    "observerName='" + observerName + '\'' +
                    '}';
        }
    }

    /**
     * 一对多中的 多
     */
    static class EventObservable extends Observable {
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
        }
    }


}
