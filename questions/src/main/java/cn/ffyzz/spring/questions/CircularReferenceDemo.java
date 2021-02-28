package cn.ffyzz.spring.questions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/12
 * @Description:
 */
public class CircularReferenceDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(CircularReferenceDemo.class);

        applicationContext.setAllowCircularReferences(true);

        applicationContext.refresh();

        Student stu = applicationContext.getBean(Student.class);

        ClassRoom classRoom = applicationContext.getBean(ClassRoom.class);

        System.out.println(stu);

        System.out.println(classRoom);

        applicationContext.close();
    }


    @Bean
    public Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Mike");
        return student;
    }

    @Bean
    public ClassRoom classRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("C111");
        return classRoom;
    }

}
