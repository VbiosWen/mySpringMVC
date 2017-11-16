package cn.vbiso.framework.bean.builder;

import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 12:27 2017/11/6
 * @Modified By:
 */
public interface BeanBuilder<T,V> {
    Map<String,T>select (V beancore);
}
