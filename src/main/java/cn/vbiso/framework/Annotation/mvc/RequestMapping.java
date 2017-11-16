package cn.vbiso.framework.Annotation.mvc;

import cn.vbiso.framework.mvc.request.HttpServletRequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 18:39 2017/11/6
 * @Modified By:
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
    HttpServletRequestMethod[] method() default {} ;
}
