package dynamicLoad;


import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
import java.io.IOException;


public class jarLoad {
  
  @SuppressWarnings("unchecked")
  private Class loadedclass;
  
  public jarLoad(String path,String classname) throws MalformedURLException,IOException,ClassNotFoundException {
    loadJar(path,classname);
  }
  
  public jarLoad(String path,String filename,String classname) throws MalformedURLException,IOException,ClassNotFoundException {
    loadJar(path+"!/"+filename,classname);
  }

  @SuppressWarnings("unchecked")
  public Class loadJar(String file,String classname) throws MalformedURLException,IOException,ClassNotFoundException {
    loadedclass = null;
    URLClassLoader classloader;
    classloader = new URLClassLoader( new URL[] { new URL("jar","",file) } );
    loadedclass = classloader.loadClass(classname);
    return loadedclass;
  }
  
  @SuppressWarnings("unchecked")
  public Class get() {
    return loadedclass;
  }
  
  @SuppressWarnings("unchecked")
  public Class getJarClass() {
    return loadedclass;
  }

}
