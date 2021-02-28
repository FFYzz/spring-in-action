package cn.ffyzz.spring.questions;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2021/2/12
 * @Description:
 */
public class Student {

    private String name;

    @Autowired
    private ClassRoom classRoom;

    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", classRoom.name=" + classRoom.getName() +
                ", id=" + id +
                '}';
    }
}
