package cn.ffyzz.spring.aop.overview;

import java.lang.reflect.Proxy;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public class JdkDynamicEchoServiceDemo {

    public static void main(String[] args) {
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{EchoService.class}, (proxyObject, method, argz) -> {
                    if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                        EchoServiceProxy echoServiceProxy = new EchoServiceProxy(new DefaultEchoServiceImpl());
                        return echoServiceProxy.echo((String) argz[0]);
                    }
                    return null;
                });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello, World");
    }

}
