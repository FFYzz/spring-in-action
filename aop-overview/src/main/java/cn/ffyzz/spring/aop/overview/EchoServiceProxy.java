package cn.ffyzz.spring.aop.overview;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/12/20
 */
public class EchoServiceProxy implements EchoService {

    private final EchoService echoService;

    public EchoServiceProxy(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) {
        long startTime = System.currentTimeMillis();
        String msg = echoService.echo(message);
        long endTime = System.currentTimeMillis();
        System.out.println("echo execute cost time: " + (endTime - startTime) + " ms.");
        return msg;
    }
}
