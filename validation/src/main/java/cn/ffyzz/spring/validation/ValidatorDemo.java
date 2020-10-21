package cn.ffyzz.spring.validation;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.*;

import java.util.Locale;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/21
 */
public class ValidatorDemo {

    public static void main(String[] args) {

        // 1. 创建 Validatior 对象
        UserValidator userValidator = new UserValidator();
        User user = new User();
        // 2. 判断是否支持为支持的对象
        System.out.println("is Supported: " + userValidator.supports(user.getClass()));
        // 3. 创建 Errors 对象
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, errors);
        // 4. 获取 MessageSource 对象
        MessageSource messageSource = createMessageSource();

        for (ObjectError objectError : errors.getAllErrors()) {
            String message = messageSource.getMessage(objectError.getCode(), objectError.getArguments(), Locale.getDefault());
            System.out.println(message);
        }

    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return clazz.isAssignableFrom(User.class);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
        }
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 所有属性不能为空");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not be null.");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null.");
        return messageSource;
    }

}
