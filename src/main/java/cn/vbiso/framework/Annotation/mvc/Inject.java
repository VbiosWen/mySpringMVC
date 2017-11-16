package cn.vbiso.framework.Annotation.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 16:19 2017/11/16
 * @Modified By:
 */
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String name() default "";
    Class<?> type() default Object.class;
}
