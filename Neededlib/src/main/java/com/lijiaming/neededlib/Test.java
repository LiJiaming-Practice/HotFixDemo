package com.lijiaming.neededlib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws Exception{
        MyClassLoader classLoader = new MyClassLoader("/Users/ASUS/Desktop");
        Class<?> cls = classLoader.findClass("com.lijiaming.testlibrary.ModifyClass");
        Object object = cls.newInstance();
        Method method = cls.getDeclaredMethod("isLoad", (Class<?>) null);
        method.invoke(object, (Object) null);
    }

}
