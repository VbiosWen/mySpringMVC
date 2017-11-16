package cn.vbiso.framework.bean.builder.impl;

import cn.vbiso.framework.Annotation.mvc.Component;
import cn.vbiso.framework.Annotation.mvc.Scope;
import cn.vbiso.framework.bean.BeanCore;
import cn.vbiso.framework.bean.builder.BeanBuilder;
import cn.vbiso.framework.bean.entity.BeanEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description: 封装bean的名字和类
 * @Date:Create in 12:28 2017/11/6
 * @Modified By:
 */
public class MvcBeanBuilder implements BeanBuilder<BeanEntity,BeanCore> {


    @Override
    public Map<String, BeanEntity> select(BeanCore beancore) {
        Map<String,BeanEntity> map=new HashMap<>();
        beancore.getClasses().forEach(clazz ->{
            String beanName=null;
            boolean isSingleton=true;
            Component component = (Component) clazz.getAnnotation(Component.class);
            beanName=component.value();
            if(beanName.equals("")){
                String clazzName = clazz.getSimpleName();
                char c = Character.toLowerCase(clazzName.charAt(0));
                beanName=c+clazzName.substring(1);
            }
           Scope scopeAnno = (Scope) clazz.getAnnotation(Scope.class);
           isSingleton=scopeAnno==null||scopeAnno.singleton();
           map.put(beanName,new BeanEntity(isSingleton,clazz,beanName));
        });
        return map;
    }
}
