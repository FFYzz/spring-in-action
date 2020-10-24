package cn.ffyzz.spring.conversion;

import java.beans.PropertyEditor;
import java.util.Properties;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/23
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        PropertyEditor propertyEditor = new StringToPropertiesEditor();
        String text = "hello = world";
        // 输入 String 类型的数据
        propertyEditor.setAsText(text);
        // 得到目标类型的数据
        Properties properties = (Properties) propertyEditor.getValue();
        System.out.println(properties);
        System.out.println(propertyEditor.getAsText());
    }

}
