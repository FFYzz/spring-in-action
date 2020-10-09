package cn.ffyzz.spring.bean.factory;

import cn.ffyzz.spring.ioc.overview.domain.User;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/8
 */
public class AnotherUserFactory implements UserFactory{

    @Override
    public User createUser() {
        User user = new User();
        user.setId(101L);
        user.setName("user from another userFactory");
        return user;
    }
}
