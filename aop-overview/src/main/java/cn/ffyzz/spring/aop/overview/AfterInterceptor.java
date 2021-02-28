package cn.ffyzz.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public interface AfterInterceptor {

    /**
     * @param proxy
     * @param method
     * @param args
     * @param result
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object result);

}
