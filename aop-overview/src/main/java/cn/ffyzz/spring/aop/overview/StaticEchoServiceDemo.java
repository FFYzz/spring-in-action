package cn.ffyzz.spring.aop.overview;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public class StaticEchoServiceDemo {

    public static void main(String[] args) {
        EchoService proxy = new EchoServiceProxy(new DefaultEchoServiceImpl());
        proxy.echo("Hello, World");
    }

}
