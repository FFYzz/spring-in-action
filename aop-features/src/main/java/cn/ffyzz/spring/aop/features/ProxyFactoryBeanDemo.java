package cn.ffyzz.spring.aop.features;

import cn.ffyzz.spring.aop.overview.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description:
 */
public class ProxyFactoryBeanDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/aop.xml");
        // 基于 JDK 动态代理生成的代理类
        EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);
        System.out.println(echoService.echo("Hello, World"));
        context.close();
    }

}
