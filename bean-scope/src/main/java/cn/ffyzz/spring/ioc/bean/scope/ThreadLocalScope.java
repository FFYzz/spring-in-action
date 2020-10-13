package cn.ffyzz.spring.ioc.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/13
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_THREAD_LOCAL_NAME = "thread local";

    NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<>("thread-local-scope") {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> map = getContext();
        Object object = map.get(name);
        if (object == null) {
            object = objectFactory.getObject();
            map.put(name, object);
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        return getContext().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // TODO do nothing
    }

    @Override
    public Object resolveContextualObject(String key) {
        return getContext().get(key);
    }

    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }

    public Map<String, Object> getContext() {
        return threadLocal.get();
    }
}
