package cn.vbiso.test;

import org.junit.Test;

import java.net.URL;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 16:02 2017/11/5
 * @Modified By:
 */
public class TestJarPath {


    @Test
    public void test1(){
        URL ur = TestJarPath.class.getProtectionDomain().getCodeSource().getLocation();
        String path=ur.getPath();
        String newpath=path.replace("/","\\");
        newpath=newpath.substring(1);
        System.out.println(path);
        System.out.println(newpath);
    }
}
