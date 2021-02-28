package cn.ffyzz.spring.aop.features;

import cn.ffyzz.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import cn.ffyzz.spring.aop.overview.DefaultEchoServiceImpl;
import cn.ffyzz.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description:
 */
public class ProxyFactoryDemo {

    public static void main(String[] args) {
        DefaultEchoServiceImpl defaultEchoService = new DefaultEchoServiceImpl();
        // 注入被代理对象
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        // 添加 Advice | EchoServiceMethodInterceptor < MethodInterceptor < Interceptor < Advice
        // 所以可以添加 Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象
        // 默认 JDK 动态生成
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello World"));
    }

}
