package cn.ffyzz.spring.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/23
 */
public class StringToPropertiesEditor extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 创建目标对象
        Properties properties = new Properties();
        try {
            // 进行转化操作
            properties.load(new StringReader(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 保存临时变量
        setValue(properties);
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : properties.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
