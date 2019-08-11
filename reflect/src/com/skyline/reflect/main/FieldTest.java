package com.skyline.reflect.main;

import com.skyline.reflect.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 使用反射操作属性
 *
 * @author zhangkepeng
 */
public class FieldTest {

    /**
     * 使用反射获取对象的属性
     *
     * @param user
     * @throws Exception
     */
    public static void getField(User user) throws Exception {

        Class clazz = Class.forName("com.skyline.reflect.entity.User");
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            // 私有属性必需授权可操作
            field.setAccessible(true);

            String name = field.getName();
            System.out.println(name);

            // 字段修饰符相加 什么都不加 是0 ， public  是1 ，private 是 2 ，protected 是 4，static 是 8 ，final 是 16。
            int modifiers = field.getModifiers();
            // 判断字段是不是静态变量
            boolean aStatic = Modifier.isStatic(modifiers);

            if (aStatic) {
                Object o = field.get(null);
                System.out.println(o);
            } else {
                Object o = field.get(user);
                System.out.println(o);
            }

        }

    }

    /**
     * 使用反射设置对象的属性
     *
     * @throws Exception
     */
    public static void setField() throws Exception {

        Class clazz = Class.forName("com.skyline.reflect.entity.User");
        User user = (User) clazz.newInstance();
        // User没有setter方法，私有属性不能用setter方法赋值，就需要使用反射机制来给私有属性赋值。
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);

        // 获取属性类型
        Class<?> type = name.getType();
        System.out.println("type: " + type);

        // 给name属性赋值
        name.set(user, "乔峰");

        // User没有getter方法，私有属性不能用getter方法取值，需要使用反射机制来取值
        String userName = (String) name.get(user);
        System.out.println("userName: " + userName);

    }

    public static void main(String[] args) throws Exception {
        getField(new User(2, "段誉", "123456", 28));

        setField();

    }
}
