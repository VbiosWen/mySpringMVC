package cn.vbiso.framework.utils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 15:37 2017/11/5
 * @Modified By:
 */
public class ClassUtil {


    /**
     * 获取包下的所有类
     * @param packageName
     * @return
     */
    public static List<String> getAllPackageClasses(String packageName) {
        String catalogueName = packageName.replace(".", "\\");
        List<String> list = new ArrayList<String>();

        try {
            //获取运行时文件所在的位置
            String runtimePath = JarUtil.getJarPath(packageName);
            if (runtimePath.contains("/")) {
                runtimePath = runtimePath.replace("/", "\\");
                runtimePath = runtimePath.substring(1);
            }
            //如果当前文件是jar包
            if (runtimePath.endsWith(".jar")) {
                JarFile jarFile = new JarFile(runtimePath);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = entries.nextElement();
                    String className = jarEntry.getName();//   cn\controller\xxx.class
                    if (className.endsWith(".class")) {
                        if (className.startsWith(catalogueName.substring(1))) {
                            //获取当前包的形式 并加入list中
                            String classPackage = className.replace("\\", ".").replace("/", ".");
                            list.add(classPackage);
                        }
                    }
                }
            } else {//当前文件是普通文件
                File file = new File(runtimePath);
                List<String> filePath = new ArrayList<String>();
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File file1 : files) {
                            List<String> fileNames = getFileNames(file1);
                            filePath.addAll(fileNames);
                        }
                        for (String s : filePath) {
                            int caLenth=catalogueName.length();
                            int runLength=runtimePath.substring(0,runtimePath.length()-caLenth-1).length();
                            String replace = s.substring(runLength).replace("\\",".");
                            list.add(replace);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static List<String> getFileNames(File file) {
        List<String> fileNames = new ArrayList<String>();
        if (file != null) {
           if(file.isDirectory()){
               File[] files = file.listFiles();
               if(files!=null){
                   for(File f:files){
                       fileNames.addAll(getFileNames(f));
                   }
               }
           }else{
               if(file.getName().endsWith(".class")){
                   fileNames.add(file.getAbsolutePath());
               }
           }
        }
        return fileNames;
    }

    public static <D extends Annotation> List<Class>
    getAllClassesByAnnotation(String pack,  Class<D> annotation) {
        List<Class> classes = getClassInstance(pack);
        Annotation annotation1 = classes.get(0).getAnnotation(annotation);
        return classes.stream().filter(aClass -> aClass.getAnnotation(annotation) != null).collect(Collectors.toList());
    }

    private static List<Class> getClassInstance(String pack){
        List<String> classes = getAllPackageClasses(pack);
        List<Class> list=new ArrayList<Class>();
        for(String clzz:classes){
            try {
                String substring = clzz.substring(0, clzz.lastIndexOf("."));
                list.add(Class.forName(clzz.substring(0,clzz.lastIndexOf("."))));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
