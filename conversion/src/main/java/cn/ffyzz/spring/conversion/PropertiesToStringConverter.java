package cn.ffyzz.spring.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/23
 */
public class PropertiesToStringConverter implements ConditionalGenericConverter {


    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.getResolvableType().equals(Properties.class)
                && targetType.getResolvableType().equals(String.class);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Properties properties = (Properties) source;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : properties.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
