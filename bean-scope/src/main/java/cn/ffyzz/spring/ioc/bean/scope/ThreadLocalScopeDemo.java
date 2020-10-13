package cn.ffyzz.spring.ioc.bean.scope;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/13
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_THREAD_LOCAL_NAME)
    public User user() {
        return createUser();
    }

    public User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Bean
    public User otherUser() {
        return createUser();
    }

    @Autowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_THREAD_LOCAL_NAME, new ThreadLocalScope());
        });

        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);
        System.out.println("=======================");

        Map<String, User> users = applicationContext.getBeansOfType(User.class);
        users.forEach((key, value) -> {
            System.out.println(users.get(key));
        });

        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("=======================");
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                // user 是共享 Bean 对象
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id :%d] user = %s%n", Thread.currentThread().getId(), user);
            });

            // 启动线程
            thread.start();
            // 强制线程执行完成
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
