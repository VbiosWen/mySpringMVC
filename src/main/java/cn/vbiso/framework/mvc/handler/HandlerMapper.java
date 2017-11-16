package cn.vbiso.framework.mvc.handler;

import cn.vbiso.framework.Annotation.mvc.RequestMapping;
import cn.vbiso.framework.Annotation.mvc.ResponseBody;
import cn.vbiso.framework.bean.BeanFactory;
import cn.vbiso.framework.bean.entity.BeanEntity;
import cn.vbiso.framework.mvc.request.HttpServletRequestMethod;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 18:32 2017/11/6
 * @Modified By:
 */
public class HandlerMapper {
    private BeanFactory beanFactory;
    private final Map<String, Handler> handlerMap = new ConcurrentHashMap<>();

    public HandlerMapper(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        init();
    }

    private void init() {
        //获取类文件
        Collection<BeanEntity> beanEntities = beanFactory.getBeanEntities();
        for (BeanEntity beanEntity : beanEntities) {
            Class beanClass = beanEntity.getBeanClass();
            String prefix = "";
            HttpServletRequestMethod[] requestMethods = {};
            RequestMapping requestMapping = (RequestMapping) beanClass.getAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                prefix = requestMapping.value();
                requestMethods = requestMapping.method();
            }
            Method[] methods = beanClass.getMethods();
            for (Method method : methods) {
                String suffix = "";
                HttpServletRequestMethod[] methodRequest = {};
                boolean isJson;
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                if (annotation != null) {
                    suffix = annotation.value();
                    methodRequest = annotation.method();
                    String uri = uriUtil(prefix, suffix);
                    isJson = method.getAnnotation(ResponseBody.class) != null;
                    if (!handlerMap.containsKey(uri)) {
                        handlerMap.put(uri, new Handler(method,uri,beanClass,RequestMethodUtils(requestMethods,methodRequest),isJson));
                    }
                }
            }
        }
    }

    private HttpServletRequestMethod[] RequestMethodUtils(HttpServletRequestMethod[] requestMethods, HttpServletRequestMethod[] methodRequest) {
        if (requestMethods.length == 0) return methodRequest;
        if (methodRequest.length == 0) return requestMethods;
        HashSet<HttpServletRequestMethod> httpServletRequestMethods = new HashSet<>();
        List<HttpServletRequestMethod> methods=new ArrayList<>();
        httpServletRequestMethods.addAll(Arrays.asList(requestMethods));
        for(HttpServletRequestMethod method:methodRequest){
            if(httpServletRequestMethods.contains(method)){
                methods.add(method);
            }
        }

        return methods.toArray(new HttpServletRequestMethod[methods.size()]);

    }

    private String uriUtil(String prefix, String suffix) {
        if (prefix.endsWith("/")) prefix = prefix.substring(0, prefix.length() - 2);
        if (suffix.startsWith("/")) suffix = suffix.substring(1);
        if (!suffix.endsWith(".action")) suffix = suffix + ".action";

        return prefix + "/" + suffix;
    }

    public Handler getHandler(HttpServletRequest req) {
        String contextPath=req.getContextPath();
        String requestURI = req.getRequestURI();
        if(requestURI.startsWith(contextPath)){
            requestURI=requestURI.substring(contextPath.length());
        }
        return handlerMap.get(requestURI);
    }
}
