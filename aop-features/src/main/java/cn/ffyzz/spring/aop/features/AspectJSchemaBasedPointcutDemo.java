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
public class AspectJSchemaBasedPointcutDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/aop.xml");
        EchoService echoService = context.getBean("echoService", EchoService.class);
        System.out.println(echoService.echo("Hello. World.."));
        context.close();
    }

}
