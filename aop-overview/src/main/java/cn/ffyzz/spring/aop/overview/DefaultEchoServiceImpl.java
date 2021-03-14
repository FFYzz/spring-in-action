package cn.ffyzz.spring.aop.overview;

import java.util.Random;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public class DefaultEchoServiceImpl implements EchoService {
    @Override
    public String echo(String message) throws RuntimeException{
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("For purpose with xml..");
        }
        return "[Echo]: " + message;
    }
}
