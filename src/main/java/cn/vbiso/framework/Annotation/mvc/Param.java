package cn.vbiso.framework.Annotation.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 19:40 2017/11/6
 * @Modified By:
 */
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
    String name() default "";
    Class type() default Object.class;
}
