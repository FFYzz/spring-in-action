package cn.ffyzz.spring.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        // 类路径
        String classPath = "cn.ffyzz.spring.aop.overview.EchoService";
        // 类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 加载
        Class<?> echoServiceClass = classLoader.loadClass(classPath);
        Method method = ReflectionUtils.findMethod(echoServiceClass, "echo", String.class);
        System.out.println(method);

        // 过滤
        ReflectionUtils.doWithMethods(echoServiceClass,
                (method1) -> {
                    System.out.println("仅抛出 RuntimeException 异常且参数只要 String 类型的方法为:" + method1);
                },
                (method1) -> {
                    Class[] parameterTypes = method1.getParameterTypes();
                    Class[] exceptionTypes = method1.getExceptionTypes();
                    return parameterTypes.length == 1
                            && parameterTypes[0] == String.class
                            && exceptionTypes.length == 1
                            && exceptionTypes[0] == RuntimeException.class;
                });
    }

}
