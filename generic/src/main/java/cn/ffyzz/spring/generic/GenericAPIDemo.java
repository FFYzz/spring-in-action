package cn.ffyzz.spring.generic;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/24
 */
public class GenericAPIDemo {

    public static void main(String[] args) {

        // 原生类型 primitive types : int float double
        Class doubleClass = double.class;

        // 数组类型 array types : int[] Object[]
        Class arrayClass = Object[].class;

        // 原始类型 raw type : java.lang.String
        Class rawType = String.class;

        // 泛型参数类型 parameterizedType
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

        System.out.println(parameterizedType.toString());

        // <E>
        Type[] typeVariables = parameterizedType.getActualTypeArguments();

        Stream.of(typeVariables)
                .map(TypeVariable.class::cast) // Type -> TypeVariable
                .forEach(System.out::println);

    }

}
