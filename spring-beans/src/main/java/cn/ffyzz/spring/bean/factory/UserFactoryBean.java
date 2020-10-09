package cn.ffyzz.spring.bean.factory;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/8
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setId(200L);
        user.setName("factory bean user");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
