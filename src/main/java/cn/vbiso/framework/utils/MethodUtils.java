package cn.vbiso.framework.utils;

import cn.vbiso.framework.Annotation.mvc.Inject;
import cn.vbiso.framework.bean.entity.MethodEntity;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @Author:VbisoWen
 * @Description:
 * @Date:Create in 17:34 2017/11/13
 * @Modified By:
 */
public class MethodUtils {
   private static final LocalVariableTableParameterNameDiscoverer discover=new LocalVariableTableParameterNameDiscoverer();

   public static List<MethodEntity>getMethodByClass(Class clzz){
      Method[] methods = clzz.getMethods();
      List<MethodEntity> methodEntityList=new ArrayList<>();
      Arrays.stream(methods).forEach(method -> methodEntityList.add(new MethodEntity(method)));
      return methodEntityList;
   }

   public static Map<String,Class> getMethodParam(Executable method){
      String[] parameters = getParamterNames(method);
      HashMap<String, Class> paramMethod = new LinkedHashMap<>();
      Class<?>[] parameterTypes = method.getParameterTypes();
      if(parameters.length!=parameterTypes.length){
         throw new RuntimeException("数据参数异常");
      }
      for(int i=0;i<parameters.length;i++){
         paramMethod.put(parameters[i],parameterTypes[i]);
      }
      return paramMethod;
   }

   private static String[] getParamterNames(Executable method) {
      if(method instanceof Method){
         return discover.getParameterNames((Method) method);
      }else if(method instanceof Constructor){
         return discover.getParameterNames((Constructor<?>) method);
      }else {
         throw new RuntimeException("不合法的参数类型");
      }
   }

   public static <T extends Annotation> Map<String,T> getMethodAnnotation(Class<T> tClass,Executable method) {
      LinkedHashMap<String, T> paramLinkMap = new LinkedHashMap<>();
      String[] paramterNames = getParamterNames(method);
      Parameter[] parameters = method.getParameters();
      for(int i=0;i<paramterNames.length;i++){
         T annotation = parameters[i].getAnnotation(tClass);
         if(annotation!=null){
            paramLinkMap.put(paramterNames[i],annotation);
         }
      }
      return paramLinkMap;
   }

   public static <T extends Annotation> Map<String,T> getMethodParamAnnotation(Class<T> inc,Executable executable) {
      LinkedHashMap<String, T> hashmap = new LinkedHashMap<>();
      String[] paramterNames = getParamterNames(executable);
      Parameter[] parameters = executable.getParameters();
      for(int i=0;i<paramterNames.length;i++){
         T annotation = parameters[i].getAnnotation(inc);
         if(annotation!=null){
           hashmap.put(paramterNames[i],annotation);
         }
      }
      return hashmap;
   }
}
