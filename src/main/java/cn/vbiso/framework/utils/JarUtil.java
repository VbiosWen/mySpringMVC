package cn.vbiso.framework.utils;

import java.net.URL;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 15:47 2017/11/5
 * @Modified By:
 */
public class JarUtil {

    public static ClassLoader getClassLoder(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static String getJarPath(String packageName){
        URL url =getClassLoder().getResource(packageName.replace(".","/"));
        return url.getPath();
    }
}
