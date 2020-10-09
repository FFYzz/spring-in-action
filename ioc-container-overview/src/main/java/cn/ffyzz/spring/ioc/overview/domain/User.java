package cn.ffyzz.spring.ioc.overview.domain;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/6
 */
public class User {

    private Long id;

    private String name;

    public static User createUser() {
        User user = new User();
        user.setName("static user");
        user.setId(20L);
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void initMethodByJavaAPI(){
        name = name + " initMethodByJavaAPI";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
