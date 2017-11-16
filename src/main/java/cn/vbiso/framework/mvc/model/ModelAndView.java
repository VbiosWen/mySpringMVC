package cn.vbiso.framework.mvc.model;

import cn.vbiso.framework.mvc.view.ServletView;
import cn.vbiso.framework.mvc.view.ViewType;

import javax.jws.WebParam;
import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 18:02 2017/11/15
 * @Modified By:
 */
public class ModelAndView {

    private ServletView view;
    private Model model;

    public ModelAndView() {
        this(null,new Model());
    }

    public ModelAndView(String viewName, Model model) {
        this.view=new ServletView(viewName);
        this.model = model;
    }

    public void addAttr(String key,Object value){
        model.addAttr(key, value);
    }
    public void addAttr(Object value){
         model.addAttr(value);
    }
    public void addAllAttrs(Map<String,Object> value){
        model.addAll(value);
    }

    public Object getAttr(String key){
        return model.getAttr(key);
    }
    public String getViewName(){
        return view.getViewName();
    }

    public void setViewName(String viewName){
        this.view.setViewName(viewName);
    }

    public ServletView getView() {
        return view;
    }

    public void setView(ServletView view) {
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setViewType(ViewType viewType){
     view.setViewType(viewType);
    }

    public ViewType getViewType(){
        return view.getViewType();
    }
}
