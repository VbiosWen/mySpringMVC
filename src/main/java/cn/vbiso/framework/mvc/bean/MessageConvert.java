package cn.vbiso.framework.mvc.bean;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 20:54 2017/11/13
 * @Modified By:
 */
public interface MessageConvert<T> {
    T convert(String message);
}
