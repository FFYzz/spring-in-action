package cn.ffyzz.spring.dependency.injection;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class AnnotationDependencyMethodInjectionDemo {

    UserHolder userHolder;

    UserHolder userHolder2;


    @Autowired
    private void init1(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    private void init2(UserHolder userHolder) {
        this.userHolder2 = userHolder;
    }

    public static void main(String[] args) {

    }

    /**
     * 方法的依赖注入
     *
     * @param user
     * @return
     */
    @Bean
    public UserHolder getUserHolder(User user) {
        // setter 方式注入
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

}
