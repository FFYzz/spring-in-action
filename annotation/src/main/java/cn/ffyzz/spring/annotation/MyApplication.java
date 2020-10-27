package cn.ffyzz.spring.annotation;

import java.lang.annotation.*;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/27
 */
@MyComponent2
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyConfiguration(name = "name")
public @interface MyApplication {
}
