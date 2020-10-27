package cn.ffyzz.spring.annotation;

import java.lang.annotation.*;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/26
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyComponent
public @interface MyComponent2 {

}
