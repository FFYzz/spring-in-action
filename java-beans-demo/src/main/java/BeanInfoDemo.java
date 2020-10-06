import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/5
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(
                        propertyDescriptor -> {
                            String name = propertyDescriptor.getName();
                            Class<?> propertyType = propertyDescriptor.getPropertyType();
                            if (name.equals("age")) {
                                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                                // 具体的 bean
//                                propertyDescriptor.createPropertyEditor();
                            }
                        }
                );
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(Integer.valueOf(text));
        }
    }

}
