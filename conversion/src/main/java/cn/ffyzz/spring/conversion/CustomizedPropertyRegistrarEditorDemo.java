package cn.ffyzz.spring.conversion;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/23
 */
public class CustomizedPropertyRegistrarEditorDemo implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(User.class, "context", new StringToPropertiesEditor());
    }
}
