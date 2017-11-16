package cn.vbiso.framework.bean.entity;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author:VbisoWen
 * @Description:  动态代理的方面实体存储
 * @Date:Create in 17:18 2017/11/6
 * @Modified By:
 */
public class AspectBean {
    private List<Method> before;
    private List<Method> after;
    private Class clzz;
    private String aspectName;

    public AspectBean(List<Method> before, List<Method> after, Class clzz, String aspectName) {
        this.before = before;
        this.after = after;
        this.clzz = clzz;
        this.aspectName = aspectName;
    }

    public List<Method> getBefore() {
        return before;
    }

    public void setBefore(List<Method> before) {
        this.before = before;
    }

    public List<Method> getAfter() {
        return after;
    }

    public void setAfter(List<Method> after) {
        this.after = after;
    }

    public Class getClzz() {
        return clzz;
    }

    public void setClzz(Class clzz) {
        this.clzz = clzz;
    }

    public String getAspectName() {
        return aspectName;
    }

    public void setAspectName(String aspectName) {
        this.aspectName = aspectName;
    }
}
