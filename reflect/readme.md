[TOC]

# 1. 为什么使用反射
先来看看什么地方都用过反射：
(1) 注解
(2) jdbc 
# 2. 反射的使用
## 2.1 通过反射获取运行时类
反射获取类有三种方法：
(1) 类名.class
~~~java
public Class getFirstClass(User user) {
    Class clazz = user.getClass();
    return clazz;
}
~~~
(2) 对象名.getClass()
~~~java
public Class getSecondClass() {
    Class clazz = User.class;
    return clazz;
}
~~~
(3) Class.forName("类的全限定名")
~~~java
public Class getThirdClass() {
    Class clazz = null;
    try {
        clazz = Class.forName("com.skyline.reflect.entity.User");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    return clazz;
}
~~~
## 2.2 通过反射获取属性
~~~java
/**
    * 使用反射设置对象的属性
    *
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

~~~

## 2.3 通过反射获取方法
~~~java
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
~~~
# 3. 反射的使用场景

本文涉及的源码，请点击[这里](https://github.com/Skyline-1993/javase/tree/master/reflect/src/com/skyline/reflect)获取。