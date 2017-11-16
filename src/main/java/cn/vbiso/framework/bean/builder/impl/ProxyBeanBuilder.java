package cn.vbiso.framework.bean.builder.impl;

import cn.vbiso.framework.Annotation.aop.After;
import cn.vbiso.framework.Annotation.aop.Aspect;
import cn.vbiso.framework.Annotation.aop.Before;
import cn.vbiso.framework.bean.BeanFactory;
import cn.vbiso.framework.bean.builder.BeanBuilder;
import cn.vbiso.framework.bean.entity.AspectBean;
import cn.vbiso.framework.bean.entity.BeanEntity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description://将bean的class信息封装在代理对象
 * @Date:Create in 17:45 2017/11/6
 * @Modified By:
 */
public class ProxyBeanBuilder implements BeanBuilder<AspectBean,BeanFactory> {

    @Override
    public Map<String, AspectBean> select(BeanFactory beancore) {
        HashMap<String, AspectBean> aspectBeanMap = new HashMap<>();
       beancore.getBeanEntities().forEach(bean->{
           List<Method> before=new ArrayList<>();
           List<Method> after=new ArrayList<>();
           Class beanClass = bean.getBeanClass();
           Aspect aspect= (Aspect) beanClass.getAnnotation(Aspect.class);
           if(aspect!=null){
               Method[] methods = beanClass.getMethods();
               for(Method method:methods){
                   if(method.getAnnotation(Before.class)!=null){
                       before.add(method);
                   }
                   if(method.getAnnotation(After.class)!=null){
                       after.add(method);
                   }
               }
               if(aspect.equals("")){
                   aspectBeanMap.put(aspect.name(),new AspectBean(before,after,bean.getBeanClass(),bean.getBeanName()));
               }else{
                   aspectBeanMap.put(aspect.name(),new AspectBean(before,after,bean.getBeanClass(),aspect.name()));
               }
           }

       });

        return aspectBeanMap;
    }
}
