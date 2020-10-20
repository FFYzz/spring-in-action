package cn.ffyzz.spring.resource;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/20
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String name = "sun.net.www.protocol.x.Handler";
        Object o = Class.forName(name).newInstance();
        System.out.println(o);

    }

}
