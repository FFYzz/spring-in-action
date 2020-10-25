package cn.ffyzz.spring.generic;

import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */

/**
 * 仅在 Spring 5.0 以下版本有效
 */
public class GenericCollectionTypeResolverDemo {

    private static ArrayList<String> list;

    private static StringList strings;

    public static void main(String[] args) throws NoSuchFieldException {

        // 返回泛型参数中的具体类型
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));

        // 没有指定具体类型，返回 null
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

        Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("list");

        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

        field = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");

        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
    }

}
