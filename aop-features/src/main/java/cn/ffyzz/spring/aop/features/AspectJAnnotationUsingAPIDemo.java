package cn.ffyzz.spring.aop.features;

import cn.ffyzz.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/28
 * @Description:
 */
public class AspectJAnnotationUsingAPIDemo {

    public static void main(String[] args) {

        Map<String, Object> cache = new HashMap<>();

        // 创建一个 AspectJProxyFactory
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(cache);
        // 添加 Aspect
        // 目前没有起到任何作用
        aspectJProxyFactory.addAspect(AspectConfiguration.class);
        // 添加 Advice
        aspectJProxyFactory.addAdvice((MethodBeforeAdvice) (method, args1, target) -> {
            if ("put".equals(method.getName()) && args1.length == 2) {
                System.out.printf("input key: %s, value: %s \n", args1[0], args1[1]);
            }
        });

        Map<String, Object> proxy = aspectJProxyFactory.getProxy();
        proxy.put("1", "A");
        proxy.put("1", "B");
        System.out.println(cache.get("1"));

    }

}
