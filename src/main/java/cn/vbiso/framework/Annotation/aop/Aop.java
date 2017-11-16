package cn.vbiso.framework.Annotation.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 13:36 2017/11/6
 * @Modified By:
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Aop {
    String[] aspct();
    boolean before() default true;
    boolean after() default true;
}
