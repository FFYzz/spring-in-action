package cn.ffyzz.spring.generic;

import cn.ffyzz.spring.ioc.overview.domain.User;

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/24
 */
public class GenericDemo {

    public static void main(String[] args) {
        Collection<String> collection = new ArrayDeque<>();
        collection.add("Hello");
        collection.add("World");
        // 报编译错误
        // 编译时类型强检查
//        collection.add(1);
        // 泛型欺骗
        Collection tmp = collection;
        // 被欺骗后编译通过
        tmp.add(1);
        tmp.add(new User());
        System.out.println(collection.toString());
    }

}
