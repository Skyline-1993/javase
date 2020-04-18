package main;

import util.FixValue;
import util.Log;

/**
 * @author zhangkepeng
 */
public class AnnotationTest {

    @FixValue
    private static String testValue;

    @Log
    public static void testLog() {
        System.out.println("测试注解");
    }

    public static void main(String[] args) {
        System.out.println(testValue);
    }
}
