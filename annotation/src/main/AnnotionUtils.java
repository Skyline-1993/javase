package main;

import util.Name;
import util.Sex;

import java.lang.reflect.Field;

/**
 * @author zhangkepeng
 */
public class AnnotionUtils {

    public static String getInfo(Class<?> cs){
        String result = "";
        Field[] declaredFields = cs.getDeclaredFields();
        for (Field field : declaredFields){
            if(field.isAnnotationPresent(Name.class)){
                Name annotation = field.getAnnotation(Name.class);
                String value = annotation.value();
                result += (field.getName() + ":" + value + "\n");
            }
            if(field.isAnnotationPresent(Sex.class)){
                Sex annotation = field.getAnnotation(Sex.class);
                String value = annotation.gender().name();
                result += (field.getName() + ":" + value + "\n");
            }
        }
        System.out.println(result);
        return result;
    }

    /*public static void main(String[] args){
        String info = getInfo(Person.class);
        System.out.println(info);
    }*/

}
