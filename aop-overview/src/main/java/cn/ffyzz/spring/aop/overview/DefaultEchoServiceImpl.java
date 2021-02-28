package cn.ffyzz.spring.aop.overview;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public class DefaultEchoServiceImpl implements EchoService {
    @Override
    public String echo(String message) {
        return "[Echo]: " + message;
    }
}
