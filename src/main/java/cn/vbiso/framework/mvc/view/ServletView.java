package cn.vbiso.framework.mvc.view;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 18:16 2017/11/15
 * @Modified By:
 */
public class ServletView {
      private String viewName;
      private ViewType viewType;

    public ServletView(String viewName) {
       this(viewName,ViewType.forward);
    }

    public ServletView(String viewName, ViewType viewType) {
        this.viewName = viewName;
        this.viewType = viewType;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }
}
