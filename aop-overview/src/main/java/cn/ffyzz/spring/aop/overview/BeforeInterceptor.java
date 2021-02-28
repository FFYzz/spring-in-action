package cn.ffyzz.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public interface BeforeInterceptor {

    /**
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);

}
