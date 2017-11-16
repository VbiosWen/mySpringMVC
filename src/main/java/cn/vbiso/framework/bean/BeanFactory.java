package cn.vbiso.framework.bean;

import cn.vbiso.framework.Annotation.aop.Aspect;
import cn.vbiso.framework.Annotation.mvc.Inject;
import cn.vbiso.framework.aop.BeanProxy;
import cn.vbiso.framework.bean.builder.BeanBuilder;
import cn.vbiso.framework.bean.builder.impl.MvcBeanBuilder;
import cn.vbiso.framework.bean.entity.AspectBean;
import cn.vbiso.framework.bean.entity.BeanEntity;
import cn.vbiso.framework.utils.MethodUtils;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 15:31 2017/11/5
 * @Modified By:
 */
public class BeanFactory {

    private BeanBuilder selector;//筛选controller层的类
    private Map<Class,String>classMap=new HashMap<>();
    private BeanCore beanCore;
    private Map<String,BeanEntity> beanEntityMap;
    private Map<Class,Object>singletonEntities=new ConcurrentHashMap<>();//存放单利模式下的bean实体
    private Map<Class,Object>multipleEntities=new ConcurrentHashMap<>();//存放多例模式下的bean实体
    private Map<Class,Boolean> Ishas=new ConcurrentHashMap<>();
    private Lock lock1=new ReentrantLock();
    private Lock lock2=new ReentrantLock();
    private BeanProxy beanProxy;//通过代理模式生成实体bean
    private boolean isaop;

    public BeanFactory(String packageName) {
        this(packageName,false);
    }

    public BeanFactory(String packageName,boolean isAop) {
           this(new BeanCore(packageName),new MvcBeanBuilder(),isAop);
    }

    public BeanFactory(BeanCore beanCore,BeanBuilder selector, boolean isaop) {
        this.selector = selector;
        this.beanCore = beanCore;
        this.isaop=false;
        init(selector);
        beanProxy=new BeanProxy(this,isaop);

    }

    private void init(BeanBuilder selector) {
        beanEntityMap=selector.select(beanCore);
        beanEntityMap.forEach((s, beanEntity) -> classMap.put(beanEntity.getBeanClass(),s));

    }

    public Collection<BeanEntity> getBeanEntities() {
        return beanEntityMap.values();
    }

    public Map<String,AspectBean> getAspectBeans(BeanBuilder beanBuilder){
        return beanBuilder.select(this);
    }

    public Object getBean(Class clzz) {
        return getBean(clzz,true);
    }

    private Object getBean(Class clzz, boolean flag) {
        return getBean(classMap.get(clzz),flag);
    }

    private Object getBean(String name,boolean flag){
        BeanEntity entity=getBeanEntity(name);
        if(entity==null){
            throw new RuntimeException();
        }
        try {
            if(entity.isSingleton()){
                lock2.lock();
                Object o = singletonEntities.get(entity);
                if(o==null){
                    o=Ishas.put(entity.getBeanClass(),true);
                    o=createBean(entity.getBeanClass(),singletonEntities);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object createBean(Class beanClass, Map<Class, Object> singletonEntities) {
         createInstance(beanClass);

    }

    private Object createInstance(Class beanClass) {
        Constructor[] constructors = beanClass.getConstructors();
        getConstructor(constructors);

    }


    private BeanEntity getBeanEntity(String name) {
        return beanEntityMap.get(name);
    }

    private Constructor getConstructor(Constructor[] constructors){
          Constructor constructor=null;
          for(Constructor constructor1:constructors){
              MethodUtils.getMethodParamAnnotation(Inject.class,constructor1);
          }
    }
}
