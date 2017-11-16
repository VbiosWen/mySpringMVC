package cn.vbiso.framework.mvc.request;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 18:36 2017/11/6
 * @Modified By:
 */
public enum HttpServletRequestMethod {
    POST("post"),GET("get"),HEAD("head");
    private final String value;

    HttpServletRequestMethod(String value) {
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
