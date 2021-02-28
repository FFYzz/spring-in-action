package cn.ffyzz.spring.questions;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/12
 * @Description:
 */
public class ClassRoom {

    private String name;

    @Autowired
    private Collection<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
