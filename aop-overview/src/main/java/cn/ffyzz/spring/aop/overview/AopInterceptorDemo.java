package cn.ffyzz.spring.aop.overview;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public class AopInterceptorDemo {

    public static void main(String[] args) {
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{EchoService.class}, (proxyObject, method, argz) -> {
                    Long methodStartTime = 0L;
                    Long endTime = 0L;
                    Long methodEndTime = 0l;
                    Object res = null;
                    try {
                        if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                            BeforeInterceptor beforeInterceptor = new TimeCountBeforeInterceptor();
                            methodStartTime = (Long) beforeInterceptor.before(proxyObject, method, argz);
                            EchoService echoService = new DefaultEchoServiceImpl();
                            res = echoService.echo((String) argz[0]);
                            AfterInterceptor afterInterceptor = new TimeCountAfterInterceptor();
                            methodEndTime = (Long) afterInterceptor.after(proxyObject, method, argz, res);
                            System.out.println("echo method execute cost time: " + (methodEndTime - methodStartTime) + " ms.");
                            // .. can do anything here
                            AfterReturnInterceptor afterReturnInterceptor = new TimeCountAfterReturnInterceptor();
                            endTime = (Long) afterReturnInterceptor.afterReturn(proxyObject, method, argz, res);
                            System.out.println("after return cost time: " + (endTime - methodStartTime) + " ms.");
                            return res;
                        }
                    } catch (Exception exception) {
                        ExceptionInterceptor exceptionInterceptor = new TimeCountExceptionInterceptor();
                        // handler exception
                        String exceptionMsg = (String) exceptionInterceptor.exception(proxyObject, method, argz, res);
                        System.out.println(exceptionMsg);
                        exception.printStackTrace();
                    } finally {
                        FinallyInterceptor finallyInterceptor = new TimeCountFinallyInterceptor(methodStartTime, endTime);
                        Long costTime = (Long) finallyInterceptor.finalize(proxyObject, method, argz, res);
                        System.out.println("finally cost time: " + costTime + " ms.");
                    }
                    return null;
                });
        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello, World");
    }
}

class TimeCountBeforeInterceptor implements BeforeInterceptor {

    @Override
    public Long before(Object proxy, Method method, Object[] args) {
        return System.currentTimeMillis();
    }
}

class TimeCountAfterInterceptor implements AfterInterceptor {

    @Override
    public Long after(Object proxy, Method method, Object[] args, Object result) {
        return System.currentTimeMillis();
    }
}

class TimeCountAfterReturnInterceptor implements AfterReturnInterceptor {

    @Override
    public Long afterReturn(Object proxy, Method method, Object[] args, Object result) {
        return System.currentTimeMillis();
    }
}

class TimeCountFinallyInterceptor implements FinallyInterceptor {

    private Long startTime;
    private Long endTime;

    public TimeCountFinallyInterceptor(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Long finalize(Object proxy, Method method, Object[] args, Object result) {
        Long cost = endTime - startTime;
        return cost;
    }
}

class TimeCountExceptionInterceptor implements ExceptionInterceptor {

    @Override
    public String exception(Object proxy, Method method, Object[] args, Object result) {
        return "handler exception";
    }
}
