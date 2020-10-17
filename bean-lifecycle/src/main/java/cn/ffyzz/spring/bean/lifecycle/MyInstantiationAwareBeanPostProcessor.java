package cn.ffyzz.spring.bean.lifecycle;

import cn.ffyzz.spring.ioc.overview.domain.SuperUser;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/14
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superuser", beanName)) {
            // 将之前的属性全部替换
            return new SuperUser();
        }
        // 返回 null 表示保持之前的 属性
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("user") && bean.getClass() == SuperUser.class) {
            return false;
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        MutablePropertyValues mps;
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && bean.getClass() == UserHolder.class) {
            if (pvs instanceof MutablePropertyValues) {
                mps = (MutablePropertyValues) pvs;
            } else {
                mps = new MutablePropertyValues();
            }
            mps.add("number", 1L);
            PropertyValue descriptor = mps.getPropertyValue("descriptor");
            if (mps.contains("descriptor")) {
                mps.removePropertyValue("descriptor");
                mps.add("descriptor", "the userHolder Update VVVV");
            }
            return mps;
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && bean.getClass() == UserHolder.class) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescriptor("the userHolder Update V5");
            return userHolder;
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && bean.getClass() == UserHolder.class) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescriptor("the userHolder Update V9");
            return userHolder;
        }
        return null;
    }

}
