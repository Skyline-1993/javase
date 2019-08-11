package com.skyline.reflect.entity;

import java.io.Serializable;

/**
 * @author zhangkepeng
 */
public class User implements Serializable {

    private static final long serialVersionUID = -689768812868163698L;

    private int id;
    private String name;
    protected String password;
    public int age;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String password, int age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }


    public String getCaptcha() {
        return "captcha";
    }

    public String login(String name,String password) {
        System.out.println(name + "：" +password);
        return "login success";
    }

    private String logout() {
        return "logout success";
    }

    protected static void staticMethod(String message) {
        System.out.println("this is static method: " + message);
    }
}
