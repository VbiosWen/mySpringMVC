package cn.vbiso.framework.aop;

import cn.vbiso.framework.bean.BeanFactory;
import cn.vbiso.framework.bean.entity.AspectBean;
import net.sf.cglib.proxy.Enhancer;

import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 18:22 2017/11/6
 * @Modified By:
 */
public class ObjectProxy {
    private Enhancer enhancer;
    private BeanFactory beanFactory;
    private Map<String,AspectBean> aspectBeanMap;

    public ObjectProxy( BeanFactory beanFactory, Map<String, AspectBean> aspectBeanMap) {
        this.beanFactory = beanFactory;
        this.aspectBeanMap = aspectBeanMap;
        init();
    }


    private void init(){
        enhancer=new Enhancer();
    }



}
