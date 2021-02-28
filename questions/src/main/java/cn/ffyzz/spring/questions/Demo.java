package cn.ffyzz.spring.questions;

import cn.ffyzz.spring.ioc.overview.domain.User;
import cn.ffyzz.spring.questions.tmp.ComponentTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/30
 */
public class Demo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

//        context.scan("cn/ffyzz/spring/questions/tmp");
        context.register(ComponentTest.class);

        context.refresh();

        User user = context.getBean(User.class);
        System.out.println(user);

        context.close();
    }


}
