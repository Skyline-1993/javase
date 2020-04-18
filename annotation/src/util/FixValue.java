package util;

import java.lang.annotation.*;

/**
 * @author zhangkepeng
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FixValue {

    // int[] id() default -1;
}
