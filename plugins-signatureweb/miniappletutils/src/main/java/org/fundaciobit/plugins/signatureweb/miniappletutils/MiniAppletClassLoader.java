package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletClassLoader {


  
  private URLClassLoader miniAppletClassLoader = null;
  
  
  public URLClassLoader getMiniAppletClassLoader() {
    
    if (miniAppletClassLoader == null) {
      
      Class<?> cls = this.getClass();
      
      URL[] urls = new URL[] { 
        FileUtils.getResourceAsURL(cls, "applet/miniapplet.jar"),
        //FileUtils.getResourceAsURL(cls, "applet/miniappletui.jar")
      };
      
      miniAppletClassLoader = new URLClassLoader(urls, String.class.getClassLoader());
    }

    return miniAppletClassLoader;

  }
  
  // XYZ 
  public URLClassLoader getMiniAppletClassLoader2() {
    
    
    {
     
      Class<?> cls = this.getClass();
      
      URL[] urls = new URL[] { 
        FileUtils.getResourceAsURL(cls, "applet/miniapplet.jar"),
        //FileUtils.getResourceAsURL(cls, "applet/miniappletui.jar")
      };
      
      miniAppletClassLoader = new URLClassLoader(urls);
    }

    return miniAppletClassLoader;

  }
  
  
  public Class<?>  loadClass(String name) throws Exception {
    Class<?> classToLoad;
    try {
      classToLoad = Class.forName (name, true, getMiniAppletClassLoader());
    } catch(java.lang.ClassNotFoundException cnfe) {
      classToLoad = Class.forName (name, true, getMiniAppletClassLoader2());
    }
    return classToLoad;
  }
  
  
  public Method getMethod(Class<?> cls, String methodName) throws Exception {
    Method method = null;
    
    
    for(Method m : cls.getMethods()) {
      if (m.getName().equals(methodName)) {
        method = m;
        break;
      }
    }
    
    if (method == null) {
      throw new Exception("No s'ha trobat el mètode '" + methodName + "' dins la classe "
          + cls);
    }
    return method;
  }
  
  
  
}
