package cn.ffyzz.spring.bean.factory;

import cn.ffyzz.spring.ioc.overview.domain.User;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/8
 */
public interface UserFactory {

    default User createUser() {
        // 默认的实现为调用 User 类的静态方法实现
        return User.createUser();
    }

}
