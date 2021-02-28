package cn.ffyzz.spring.aop.overview;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/27
 * @Description:
 */
public class CGLibDynamicProxyDemo {

    public static void main(String[] args) {

        // 创建一个 Enhancer 实例
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(DefaultEchoServiceImpl.class);
        // 指定拦截的接口
        enhancer.setInterfaces(new Class[]{EchoService.class});
        // 设置回调
        enhancer.setCallback((MethodInterceptor) (source, method, objectArgs, methodProxy) -> {
            long startTime = System.currentTimeMillis();
            // Source -> CGLIB 子类
            // 目标类  -> DefaultEchoService
            // 错误使用
//                Object result = method.invoke(source, args);
            // 正确的方法调用
            Object result = methodProxy.invokeSuper(source, objectArgs);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.println("[CGLIB 字节码提升] echo 方法执行的实现：" + costTime + " ms.");
            return result;
        });
        // 创建代理对象
        EchoService echoService = (EchoService) enhancer.create();
        // 输出执行结果
        echoService.echo("HHHHH");


    }

}
