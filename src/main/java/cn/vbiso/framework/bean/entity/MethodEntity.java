package cn.vbiso.framework.bean.entity;

import cn.vbiso.framework.utils.MethodUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 19:53 2017/11/13
 * @Modified By:
 */
public class MethodEntity {

    private Class<?> returnType;
    private Map<Class<? extends Annotation>,Annotation> annotationMap=new HashMap<>();
    private Method method;
    private Map<String,Class> param;

    public MethodEntity(Method method) {
        this.method = method;
        returnType=method.getReturnType();
        Arrays.stream(method.getAnnotations()).forEach(annotation -> annotationMap.put(annotation.annotationType(),annotation));
        param= MethodUtils.getMethodParam(method);
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public Map<Class<? extends Annotation>, Annotation> getAnnotationMap() {
        return annotationMap;
    }

    public Method getMethod() {
        return method;
    }

    public Map<String, Class> getParam() {
        return param;
    }
}
