package cn.ffyzz.spring.aop.overview;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/5/16
 * @Description:
 */
public class ProxyEchoService implements EchoService {

    private final EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) throws RuntimeException {
        long startTime = System.currentTimeMillis();
        String res = echoService.echo(message);
        long endTime = System.currentTimeMillis();
        return "echo 方法执行的结果为: " + res + " , 耗时为: " + (endTime - startTime) + " ms";
    }
}
