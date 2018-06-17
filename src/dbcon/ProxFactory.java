package dbcon;

import java.lang.reflect.Proxy;

public class ProxFactory {


    public static Object getProx(Class... clazz ) {
        Class [] classes =null;
      if (clazz.length != 0)
         classes  =new Class[clazz.length];

        for (int i = 0; i < clazz.length; i++) {

           classes[i] = clazz[i];
        }
        return Proxy.newProxyInstance(ProxFactory.class.getClassLoader(), classes, Singleton.INSTANCE.getInstance());
    }
}
