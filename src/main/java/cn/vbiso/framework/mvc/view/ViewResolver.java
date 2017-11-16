package cn.vbiso.framework.mvc.view;

import cn.vbiso.framework.mvc.model.ModelAndView;
import cn.vbiso.framework.mvc.request.HttpServletRequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 18:18 2017/11/15
 * @Modified By:
 */
public class ViewResolver {

    private String suffix;
    private String preffix;

    public ViewResolver(String suffix, String preffix) {
        this.suffix = suffix;
        this.preffix = preffix;
    }

    public ModelAndView getView(ModelAndView modelAndView, HttpServletRequest request){
        String viewName=modelAndView.getViewName();
        if(viewName==null){
            viewName=request.getRequestURI();
        }else{
            if(viewName.startsWith("redirect:"))
                modelAndView.setViewType(ViewType.redirect);
        }
        viewName=uriUtil(uriUtil(preffix,viewName),suffix);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    private String uriUtil(String preffix,String suffix){
            if(preffix.endsWith("/")) preffix=preffix.substring(0,preffix.length()-2);
            if(suffix.startsWith("/")) suffix=suffix.substring(1);
            if(suffix.equals(""))
                return preffix;
            return preffix+"/"+suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPreffix() {
        return preffix;
    }

    public void setPreffix(String preffix) {
        this.preffix = preffix;
    }
}
