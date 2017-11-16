package cn.test;

import cn.vbiso.framework.Annotation.mvc.Component;
import cn.vbiso.framework.Annotation.mvc.RequestMapping;
import cn.vbiso.framework.bean.BeanCore;
import cn.vbiso.framework.bean.BeanFactory;
import cn.vbiso.framework.bean.builder.BeanBuilder;
import cn.vbiso.framework.bean.builder.impl.MvcBeanBuilder;
import cn.vbiso.framework.bean.entity.BeanEntity;
import cn.vbiso.framework.mvc.handler.HandlerMapper;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 22:46 2017/11/5
 * @Modified By:
 */
@RequestMapping("/test")
@Component
public class controller {


    public static void main(String[] args){
        BeanFactory beanFactory = new BeanFactory("cn.test");
        HandlerMapper handlerMapper = new HandlerMapper(beanFactory);
    }

    @RequestMapping("/hello")
    public String mapping(String id){
        return id;
    }
}
