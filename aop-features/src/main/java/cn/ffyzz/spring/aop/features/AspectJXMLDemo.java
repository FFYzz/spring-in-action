package cn.ffyzz.spring.aop.features;

import cn.ffyzz.spring.aop.overview.DefaultEchoServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description:
 */
@Aspect
@Configuration
public class AspectJXMLDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/aop.xml");
        context.close();
    }

}
