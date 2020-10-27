package cn.ffyzz.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/27
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
// 方法一 通过 Configuration Class
//@Import(HelloWorldConfiguration.class)
// 方法二 通过实现 ImportSelector 接口
//@Import(HelloWorldImportSelector.class)
// 方法三 基于 ImportBeanDefinitionRegistrar 接口实现
@Import(HelloWorldImportBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld {
}
