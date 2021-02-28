package cn.ffyzz.spring.aop.features;

import cn.ffyzz.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import cn.ffyzz.spring.aop.features.pointcut.EchoServicePointcut;
import cn.ffyzz.spring.aop.overview.DefaultEchoServiceImpl;
import cn.ffyzz.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description:
 */
public class PointcutAPIDemo {

    public static void main(String[] args) {
        // 引入 Pointcut
        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

        // 通过 Advisor 适配 Pointcut 与 Advice
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(echoServicePointcut, new EchoServiceMethodInterceptor());

        // 创建被代理类
        EchoService echoService = new DefaultEchoServiceImpl();

        // 创建代理类
        ProxyFactory proxyFactory = new ProxyFactory(echoService);

        // 添加 Advisor
        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        // 获取代理对象
        EchoService proxyEchoService = (EchoService) proxyFactory.getProxy();

        System.out.println(proxyEchoService.echo("Hello, world..."));

    }

}
