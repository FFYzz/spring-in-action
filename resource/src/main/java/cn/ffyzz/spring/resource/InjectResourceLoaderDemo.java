package cn.ffyzz.spring.resource;

import cn.ffyzz.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/20
 */
public class InjectResourceLoaderDemo implements ResourceLoaderAware {

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    private ResourceLoader resourceLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println("autowiredResourceLoader == resourceLoader : " + (resourceLoader == autowiredResourceLoader));
        System.out.println("applicationContext == resourceLoader : " + (resourceLoader == applicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectResourceLoaderDemo.class);

        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
