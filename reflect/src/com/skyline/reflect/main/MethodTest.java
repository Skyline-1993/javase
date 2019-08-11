package com.skyline.reflect.main;

import com.skyline.reflect.entity.User;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 使用反射操作方法
 *
 * @author zhangkepeng
 */
public class MethodTest {

    public static void getMethod() throws Exception {
        Class clazz = Class.forName("com.skyline.reflect.entity.User");
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method method : declaredMethods) {
            // 私有方法必需授权可操作
            method.setAccessible(true);

            String name = method.getName();

            // 字段修饰符相加 什么都不加 是0 ， public  是1 ，private 是 2 ，protected 是 4，static 是 8 ，final 是 16。
            int modifiers = method.getModifiers();
            // 判断方法是不是静态方法
            boolean aStatic = Modifier.isStatic(modifiers);
            if (aStatic) {
                System.out.println(name + "方法是静态方法");
            } else {
                System.out.println(name + "方法不是静态方法");
            }

        }

    }

    public static void main(String[] args) {

        // try {
        //     getMethod();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        try {

            Class clazz = Class.forName("com.skyline.reflect.entity.User");
            User user = (User) clazz.newInstance();

            // 非静态方法必需由对象调用，故invoke方法的第一个参数必需是要调用该方法的实例对象。其他参数为可变参数，是该方法的参数。
            Method login = clazz.getDeclaredMethod("login", String.class, String.class);
            login.invoke(user, "zhangsan","123456");

            // 如果要调用静态方法，不需要实例对象，可以传null值
            Method staticMethod = clazz.getDeclaredMethod("staticMethod", String.class);
            staticMethod.setAccessible(true);
            staticMethod.invoke(null,"hello reflection");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
