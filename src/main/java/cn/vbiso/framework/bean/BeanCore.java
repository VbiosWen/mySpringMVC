package cn.vbiso.framework.bean;

import cn.vbiso.framework.Annotation.mvc.Component;
import cn.vbiso.framework.utils.ClassUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 15:33 2017/11/5
 * @Modified By:
 */
public class BeanCore {
     private final String packageName;
     private List<Class> classes=new ArrayList<>();

    public BeanCore(String packageName) {
        this.packageName = packageName;
        init();
    }

    private void init(){
        String[] split = packageName.split(",");
        for(String s:split){

            this.classes.addAll(ClassUtil.getAllClassesByAnnotation(s, Component.class));
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public List<Class> getClasses() {
        return classes;
    }
}
