package cn.ffyzz.spring.dependency.injection;

import cn.ffyzz.spring.dependency.injection.annotation.InjectUser;
import cn.ffyzz.spring.dependency.injection.annotation.MyAutowired;
import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/11
 */
public class AnnotationDependencyInjectionResolutionDemo {


    @Autowired
    @Lazy
    private User lazyUser;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private Collection<User> userCollection;

    @Autowired
    private Optional<User> optionalUser;

    @MyAutowired
    private User myUser;

    @InjectUser
    private User injectUser;

    /**
     * 需要加上 static 提前初始化 bean
     * 如果不加的话 AutowiredAnnotationBeanPostProcessor bean 会依赖 AnnotationDependencyInjectionResolutionDemo
     * 导致发生错误
     *
     * @return
     */
//    @Bean
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // 替换原来默认的 types
//        Set<Class<? extends Annotation>> types = new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, MyAutowired.class, InjectUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(types);
//        return beanPostProcessor;
//    }
    @Bean
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // 替换原来默认的
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

    // 依赖查找
    // expected IN DependencyDescriptor:
    // fieldName = user
    // required = true
    // eager = true
    // declaringClass = AnnotationDependencyInjectionResolutionDemo
    //
    @Autowired
    private User user;


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user:" + demo.user);
        System.out.println("demo.optionalUser.get(): " + demo.optionalUser.get());
        System.out.println("demo.userCollection: " + demo.userCollection);
        System.out.println("demo.userObjectProvider: " + demo.userObjectProvider.getObject());
        System.out.println("demo.myUser: " + demo.myUser);
        System.out.println("demo.injectUser: " + demo.injectUser);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
