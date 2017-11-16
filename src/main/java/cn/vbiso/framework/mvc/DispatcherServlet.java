package cn.vbiso.framework.mvc;

import cn.vbiso.framework.bean.BeanFactory;
import cn.vbiso.framework.mvc.handler.HandleAdapter;
import cn.vbiso.framework.mvc.handler.Handler;
import cn.vbiso.framework.mvc.handler.HandlerMapper;
import cn.vbiso.framework.mvc.model.ModelAndView;
import cn.vbiso.framework.mvc.request.HttpServletRequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 15:20 2017/11/5
 * @Modified By:
 */
@WebServlet(name = "myServlet",
        urlPatterns = "*.action",
        initParams = {
                @WebInitParam(name = "basePackageName", value = "cn.vbiso.controller"),
                @WebInitParam(name = "prefix", value = ""),
                @WebInitParam(name = "suffix", value = "")
        })
public class DispatcherServlet extends HttpServlet {
    private static final ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");
    private String basePackageName;
    private String prefix;
    private String suffix;
    private BeanFactory beanFactory;
    private HandlerMapper handlerMapper;
    private HandleAdapter handleAdapter;

    /**
     * 收集指定包下的所有类,构造BeanCore
     * 收集指定注解的或继承的类的bean,并生成对应的handler
     * 构造handlerAdapter
     * 构造handlermapper
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        basePackageName = getInitParameter("basePackageName");
        prefix = getInitParameter("prefix");
        suffix = getInitParameter("suffix");
        beanFactory = new BeanFactory(basePackageName);
        handlerMapper = new HandlerMapper(beanFactory);
        handleAdapter = new HandleAdapter(beanFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatch(req, resp, HttpServletRequestMethod.GET);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            dispatch(req, resp, HttpServletRequestMethod.POST);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, HttpServletRequestMethod method) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Handler handler = handlerMapper.getHandler(req);
        if (handler == null) {
            resp.sendError(404, "请求信息错误");
            return;
        }
        if (!handler.isSuppotMethod(method)) {
            String protocol = req.getProtocol();
            String msg =lStrings.getString("http.method_"+method.getValue()+"_not suppoted");
            if(protocol.endsWith("1.1")){
                resp.sendError(405,msg);
            }else resp.sendError(400,msg);
            return;
        }
        ModelAndView modelAndView=handleAdapter.handler(handler,req,resp);

    }
}
