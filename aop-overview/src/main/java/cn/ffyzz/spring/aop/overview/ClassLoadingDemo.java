package cn.ffyzz.spring.aop.overview;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/5/16
 * @Description:
 */
public class ClassLoadingDemo {

    // JDK 11 和 JDK 8 会有不同的输出
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader parentClassLoader = classLoader;
        System.out.println(parentClassLoader);
        while (true) {
            parentClassLoader = parentClassLoader.getParent();
            if (parentClassLoader == null) {
                break;
            }
            System.out.println(parentClassLoader);
        }
    }

}
