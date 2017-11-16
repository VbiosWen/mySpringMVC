package cn.vbiso.framework.mvc.handler;

import cn.vbiso.framework.Annotation.mvc.Param;
import cn.vbiso.framework.Annotation.mvc.ResponseBody;
import cn.vbiso.framework.mvc.request.HttpServletRequestMethod;
import cn.vbiso.framework.utils.MethodUtils;


import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 19:32 2017/11/6
 * @Modified By:
 */
public class Handler {
    private Method method;//最终执行的方法
    private String url;//访问的地址
    private Class clzz;//所属类
    private HashSet<HttpServletRequestMethod> suppotedMethod;//网页支持的请求类型 get post ...
    private boolean isJson;
    private Map<String, Class> typeMap = new LinkedHashMap<>();//参数集合
    private Map<String, Param> annonationMap = new LinkedHashMap<>();
    private Object paramerNames;

    public Handler(Method method, String url, Class clzz, HttpServletRequestMethod[] suppotedMethod, boolean isJson) {
        this.method = method;
        this.url = url;
        this.clzz = clzz;
        this.suppotedMethod=new HashSet<>();
        this.suppotedMethod.addAll(Arrays.asList(suppotedMethod));
        this.isJson = isJson;
        this.typeMap = typeMap;
        this.annonationMap = annonationMap;
        init();
    }

    private void init(){
        ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
        isJson=responseBody!=null;
        typeMap= MethodUtils.getMethodParam(method);
        annonationMap = MethodUtils.getMethodAnnotation(Param.class, method);
    }


    public Method getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Class getClzz() {
        return clzz;
    }

    public HashSet<HttpServletRequestMethod> getSuppotedMethod() {
        return suppotedMethod;
    }

    public boolean isJson() {
        return isJson;
    }

    public Map<String, Class> getTypeMap() {
        return typeMap;
    }

    public Map<String, Param> getAnnonationMap() {
        return annonationMap;
    }

    public boolean isSuppotMethod(HttpServletRequestMethod method) {

        return suppotedMethod.contains(method);
    }

    public int getParamCount(){
        return typeMap.size();
    }

    public String[] getParamerNames() {
        return typeMap.keySet().toArray(new String[typeMap.keySet().size()]);
    }

    public Class[] getParamerTypes(){
        return typeMap.values().toArray(new Class[typeMap.keySet().size()]);
    }

    public Class getTypeByName(String paramerName) {
        return typeMap.get(paramerName);
    }

    public Param getAnnonationByParamName(String paramName) {
        return annonationMap.get(paramName);
    }
}
