package cn.ffyzz.spring.validation;

import cn.ffyzz.spring.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/22
 */
public class BeanIntrospectorDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(System.out::println);
        System.out.println("------------");
        Arrays.stream(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }

}
