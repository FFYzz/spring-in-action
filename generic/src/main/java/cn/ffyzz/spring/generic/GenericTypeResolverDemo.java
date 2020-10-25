package cn.ffyzz.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/24
 */
public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        displayReturnTypeInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
        displayReturnTypeInfo(GenericTypeResolverDemo.class, List.class, "getList");
        displayReturnTypeInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

        Map<TypeVariable, Type> typeVariableTypeMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableTypeMap);
    }

    public static ArrayList<Object> getList() { // 泛型参数类型具体化
        return null;
    }

    public static String getString() {
        return null;
    }

    public static StringList getStringList() {
        return null;
    }

    private static void displayReturnTypeInfo(Class<?> containingClass,
                                       Class<?> genericInfo, String methodName, Class... arguments) throws NoSuchMethodException {

        Method method = containingClass.getMethod(methodName);

        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, GenericTypeResolverDemo.class);

        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s\n", methodName, containingClass.getSimpleName(), returnType);

        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericInfo);

        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s\n", methodName, containingClass, returnTypeArgument);

    }


}
