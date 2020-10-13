package cn.ffyzz.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/9
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {

        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类注册为 bean
        applicationContext.register(HierarchicalDependencyLookupDemo.class);


        // ConfigurableListableBeanFactory 实现了 ListableBeanFactory 和 ConfigurableBeanFactory
        // ConfigurableBeanFactory 实现了 HierarchicalBeanFactory
        // 1. 获取到一个层次性的 beanFactory
        ConfigurableListableBeanFactory configurableListableBeanFactory = applicationContext.getBeanFactory();
        // 不存在 parentBeanFactory
//        System.out.println(configurableListableBeanFactory.getParentBeanFactory());
        // 2. 设置 parent BeanFactory
        HierarchicalBeanFactory hierarchicalBeanFactory = createParentBeanFactory();
        configurableListableBeanFactory.setParentBeanFactory(hierarchicalBeanFactory);
//        System.out.println("当前 configurableListableBeanFactory 的 Parent BeanFactory ： " + configurableListableBeanFactory.getParentBeanFactory());

        // 3. 根据 bean 名称查找
        displayContainsLocalBean(configurableListableBeanFactory, "user");
        displayContainsLocalBean(hierarchicalBeanFactory, "user");

        displayContainsBean(configurableListableBeanFactory, "user");
        displayContainsBean(hierarchicalBeanFactory, "user");


        // 启动应用上下文
        applicationContext.refresh();
        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name : %s] : %s\n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name : %s] : %s\n", beanFactory, beanName,
                beanFactory.containsBean(beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory != null && parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalParentBean = (HierarchicalBeanFactory) parentBeanFactory;
            return containsBean(hierarchicalParentBean, beanName);
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static ConfigurableListableBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        reader.loadBeanDefinitions(resourcePath);
        return defaultListableBeanFactory;
    }

}
