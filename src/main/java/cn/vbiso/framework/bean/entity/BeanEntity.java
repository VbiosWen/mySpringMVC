package cn.vbiso.framework.bean.entity;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 12:30 2017/11/6
 * @Modified By:
 */
public class BeanEntity {

    private boolean isSingleton;
    private Class beanClass;
    private String beanName;

    public BeanEntity(boolean isSingleton, Class beanClass, String beanName) {
        this.isSingleton = isSingleton;
        this.beanClass = beanClass;
        this.beanName = beanName;
    }

    public boolean isSingleton() {
        return isSingleton;
    }

    public void setSingleton(boolean singleton) {
        isSingleton = singleton;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
