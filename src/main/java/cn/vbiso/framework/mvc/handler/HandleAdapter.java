package cn.vbiso.framework.mvc.handler;

import cn.vbiso.framework.Annotation.mvc.Param;
import cn.vbiso.framework.bean.BeanFactory;
import cn.vbiso.framework.mvc.bean.DataBinder;
import cn.vbiso.framework.mvc.bean.MessageConvert;
import cn.vbiso.framework.mvc.model.Model;
import cn.vbiso.framework.mvc.model.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 20:52 2017/11/13
 * @Modified By:
 */
public class HandleAdapter {

    private BeanFactory beanFactory;
    private DataBinder dataBinder;
    private MessageConvert convert;

    public HandleAdapter(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ModelAndView handler(Handler handler, HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView modelAndView=null;
        Model model=null;
        String[] paramerNames = handler.getParamerNames();
        Object[] object=new Object[handler.getParamCount()];
        int i=0;
        for(String paramName:paramerNames){
            Class typeByName = handler.getTypeByName(paramName);
            if(typeByName==HttpServletRequest.class){
                object[i]=req;
            }else if(typeByName==HttpServletResponse.class){
                object[i]=resp;
            }else if(typeByName==Model.class){
                model=new Model();
                object[i]=model;
            }else if(typeByName==ModelAndView.class){
                modelAndView=new ModelAndView();
                object[i]=modelAndView;
            }else {
                try {
                    Param param= handler.getAnnonationByParamName(paramName);
                    String name=paramName;
                    if(param!=null){
                        if(!param.name().equals("")){
                           name=param.name();
                        }
                    }
                    if(typeByName==String.class){
                        object[i]=req.getParameter(name);
                    }else if(typeByName==String[].class){
                        object[i]=req.getParameter(name+"[]");
                    }else if(typeByName==Integer.class){
                        object[i]=Integer.valueOf(req.getParameter(name));
                    }else if(typeByName==Integer[].class){
                        String[] paramters=req.getParameterValues(name+"[]");
                       Integer[] integers= new Integer[paramters.length];
                       object[i]=integers;
                       for(int i1=0;i1<integers.length;i++){
                           integers[i1]=Integer.valueOf(paramters[i1]);
                       }
                    }else {
                        Object obj=typeByName.newInstance();
                        if(obj instanceof Map||obj instanceof Collection){
                            Field[] fields = typeByName.getDeclaredFields();
                            for(Field field:fields){
                                field.setAccessible(true);
                                String parmater=req.getParameter(field.getName());
                                if(parmater!=null){
                                    field.set(obj,parmater);
                                }
                            }
                            object[i]=obj;
                        }
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
         handler.invoke(beanFactory.getBean(handler.getClzz()),object)

     return null;
    }
}
