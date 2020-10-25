package cn.ffyzz.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/25
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {
        // 工厂创建
        // StringList <- ArrayList<String> <- AbstractList <- List <- Collection <- Object
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        System.out.println(resolvableType.getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType().getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType().getSuperType().getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType().getSuperType().getSuperType().getSuperType());

        System.out.println(resolvableType.getSource());

        System.out.println(resolvableType.asCollection().resolve());

        System.out.println(resolvableType.asCollection().resolveGeneric(0));


    }

}
