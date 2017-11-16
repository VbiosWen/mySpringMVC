package cn.vbiso.framework.aop;

import cn.vbiso.framework.bean.BeanFactory;
import cn.vbiso.framework.bean.builder.impl.ProxyBeanBuilder;
import cn.vbiso.framework.bean.entity.AspectBean;

import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 17:12 2017/11/6
 * @Modified By:
 */
public class BeanProxy {
     private boolean isAop;
     private BeanFactory beanFactory;
     private Map<String,AspectBean> aspectBeans;
     private ObjectProxy objectProxy;


    public BeanProxy(BeanFactory beanFactory, boolean isaop) {
        this.isAop=isaop;
        this.beanFactory=beanFactory;
        init();
    }

    private void init(){
        aspectBeans=beanFactory.getAspectBeans(new ProxyBeanBuilder());
        objectProxy=new ObjectProxy(beanFactory,aspectBeans);
    }
}
