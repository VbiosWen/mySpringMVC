package cn.vbiso.framework.mvc.model;

import org.springframework.core.Conventions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 18:03 2017/11/15
 * @Modified By:
 */
public class Model {

    private Map<String,Object> attr;

    public Model() {
        this(new HashMap<String,Object>());
    }

    public Model(Map<String, Object> attr) {
        this.attr = attr;
    }

    public void addAttr(String key,Object value){
        this.attr.put(key,value);
    }

    public void addAttr(Object value){
        if(value instanceof Collection &&((Collection) value).isEmpty()){
            return;
        }
        attr.put(Conventions.getVariableName(value),value);
    }

    public void addAll(Map<String,Object> attr){
        this.attr.putAll(attr);
    }


    public Object getAttr(String key) {
        return attr.get(key);
    }

    public void setAttr(Map<String, Object> attr) {
        this.attr = attr;
    }
    public Map<String,Object> getModel(){
        return this.attr;
    }
}
