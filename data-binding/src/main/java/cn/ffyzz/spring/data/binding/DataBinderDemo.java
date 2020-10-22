package cn.ffyzz.spring.data.binding;

import cn.ffyzz.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/22
 */
public class DataBinderDemo {

    public static void main(String[] args) {

        // 0. 创建对象
        User user = new User();
        // 1. 创建 DataBinder
        DataBinder dataBinder = new DataBinder(user);
        // 2. 创建属性
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tizs");
        map.put("id", 2L);
        map.put("age", 108);
        map.put("company.name", "TTTTT");
        map.put("xxxx", "1222");
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues(map);

        // 2.1 设置 绑定控制参数
        // 设置后抛出异常
//        dataBinder.setIgnoreUnknownFields(false);
        dataBinder.setAutoGrowNestedPaths(false);

        dataBinder.setIgnoreInvalidFields(true);

        dataBinder.setRequiredFields("name", "age", "company.name", "city");
        // 3. 执行绑定
        dataBinder.bind(mutablePropertyValues);

        // 4. 查看数据
        System.out.println(user);

        // 5. 获取绑定结果
        BindingResult result = dataBinder.getBindingResult();
        // 获绑定结果，包含错误文案 code，不会抛出异常
        System.out.println(result);
    }

}
