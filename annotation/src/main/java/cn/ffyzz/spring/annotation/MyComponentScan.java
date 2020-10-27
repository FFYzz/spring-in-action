package cn.ffyzz.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

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
@ComponentScan
public @interface MyComponentScan {

    // 隐式别名
    // 多态行为，覆盖 "父"注解 @ComponentScan 的 value 属性
    @AliasFor(annotation = ComponentScan.class, attribute = "value")
    String[] scanBasePackages() default {"#"};





}
