package com.skyline.reflect.main;

import com.skyline.reflect.entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取运行时类，并展示运行时类api
 *
 * @author zhangkepeng
 */
public class ClassTest {

    /**
     * 获取运行时类方法一,使用 对象.getClass()
     *
     * @param user
     * @return
     * @throws Exception
     */
    public Class getFirstClass(User user) {
        Class clazz = user.getClass();
        return clazz;
    }

    /**
     * 获取运行时类方法二,使用 类名.getClass()
     *
     * @return
     */
    public Class getSecondClass() {
        Class clazz = User.class;
        return clazz;
    }

    /**
     * 获取运行时类方法三,使用 Class.forName("类的全限定名");
     *
     * @return
     */
    public Class getThirdClass() {
        Class clazz = null;
        try {
            clazz = Class.forName("com.skyline.reflect.entity.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clazz;
    }

    public static void main(String[] args) throws Exception {

        ClassTest test = new ClassTest();
        Class clazz = test.getThirdClass();

        // 获取类名
        String clazzName = clazz.getName();

        // 获取类名(不包括包名)
        String simpleName = clazz.getSimpleName();

        // 获取父类
        Class superclass = clazz.getSuperclass();

        // 获取类的包名
        Package aPackage = clazz.getPackage();

        // 获取“类”实现的接口
        Class[] interfaces = clazz.getInterfaces();


        // 获取指定public修饰的属性  反射中会将运行时类中的属性封装为Field对象
        Field age = clazz.getField("age");

        // 获取运行时类的所有公共属性，即public修饰的属性
        Field[] publicFields = clazz.getFields();

        // 获取运行时类的指定私有属性  此方法可以获取类中的所有属性，不限于私有属性
        Field name = clazz.getDeclaredField("name");

        // 获取运行时类的所有属性(包括私有属性)
        Field[] declaredFields = clazz.getDeclaredFields();


        // 获取运行时类指定的公共方法
        Method getCaptcha = clazz.getMethod("getCaptcha");
        // 获取运行时类带参数的方法  第一个参数为方法名，之后的参数为可变参数，代表获取方法的形参类型
        Method login = clazz.getMethod("login", String.class, String.class);

        // 获取运行时类的所有公共方法(包括从父类中继承的方法，但不包括构造方法)
        Method[] publicMethods = clazz.getMethods();

        // 获取运行时类的指定私有方法 此方法可以获取类中的所有方法，不限于私有方法
        Method logout = clazz.getDeclaredMethod("logout");

        // 获取运行时类的所有方法(包括私有方法，不包括从父类中继承的方法，不包括构造方法)
        Method[] declaredMethods = clazz.getDeclaredMethods();


        // 获取指定的public构造方法
        Constructor constructor = clazz.getConstructor();
        Constructor constructor1 = clazz.getConstructor(int.class);

        // 获取所有public构造方法
        Constructor[] constructors = clazz.getConstructors();

        // 获取指定的非public的构造方法
        Constructor declaredConstructor = clazz.getDeclaredConstructor();

        // 获取所有的构造方法(包括私有的)
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();


    }
}
