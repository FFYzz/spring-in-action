package cn.ffyzz.spring.questions.tmp;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/30
 */
//@Component
//@Service
//@Controller
//@Repository
public class ComponentTest {

    @Bean
    public User getUser() {
        User user = new User();
        user.setId(2l);
        user.setName("Userrrrr");
        return user;
    }

}
