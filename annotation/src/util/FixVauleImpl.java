package util;

import java.lang.reflect.Field;

/**
 * @author zhangkepeng
 */
public class FixVauleImpl {

    public void fixValue() throws ClassNotFoundException, IllegalAccessException {
        Class clazz = Class.forName("main.AnnotationTest");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            boolean flag = field.isAnnotationPresent(FixValue.class);
            if (flag) {
                Class type = field.getType();
                if (type == String.class) {
                    field.set(this, "");
                }
            }
        }

    }
}
