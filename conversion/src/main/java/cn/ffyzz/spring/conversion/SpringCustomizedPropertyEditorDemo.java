package cn.ffyzz.spring.conversion;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/23
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editor-context.xml");

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        applicationContext.close();
    }

}
