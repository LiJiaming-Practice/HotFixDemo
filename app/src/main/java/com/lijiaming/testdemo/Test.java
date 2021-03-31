package com.lijiaming.testdemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader("/Users/ASUS/Desktop");
        classLoader.getParent();
        Class<?> cls = null;

        {
            try {
                cls = classLoader.findClass("com.lijiaming.testlibrary.ModifyClass");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Object object = null;

        {
            try {
                object = cls.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

        Method method = null;

        {
            try {
                method = cls.getDeclaredMethod("isLoad", null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                method.invoke(object,null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }





}
