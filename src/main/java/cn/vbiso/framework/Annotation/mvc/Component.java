package cn.vbiso.framework.Annotation.mvc;

import java.lang.annotation.*;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 16:48 2017/11/5
 * @Modified By:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
