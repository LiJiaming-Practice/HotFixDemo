package com.lijiaming.testdemo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MyClassLoader extends ClassLoader{

    private String myClassPath;  //加载class目录

    private Map<String,Class<?>> loadedClasses = new HashMap<String, Class<?>>();  //存放已加载class

    private static MyClassLoader INSTANCE;

    private MyClassLoader(){
        super(MyClassLoader.class.getClassLoader().getParent());
    }

    public MyClassLoader(String classPath){
        myClassPath = classPath;
    }

    /**
     * 初始化
     */
    public static void init(){
        INSTANCE = new MyClassLoader();
        try{
            INSTANCE.setParentClassLoader(MyClassLoader.class.getClassLoader());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过package找要加载的class
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File clsFile = new File(myClassPath, getClassName(name));

        //以下操作将Class文件转换成二进制流

        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = null;
        try{
            fis = new FileInputStream(clsFile);

            int data = 0;

            while ((data = fis.read()) != -1){
                baos.write(data);
            }

            b = baos.toByteArray();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (fis != null){
                try{
                    baos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return defineClass(name, b,0, b.length);
    }

    /**
     * 自定义加载类逻辑
     */

    @Override
    protected  Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        //检查class是否已被加载
        if (loadedClasses.containsKey(name)){
            return loadedClasses.get(name);
        }
        return super.loadClass(name,resolve);
    }


    /**
     * 替换MyClassLoader为默认ClassLoader
     */
    private void setParentClassLoader(ClassLoader classLoader) throws Exception{
        Field field = ClassLoader.class.getDeclaredField("parent");
        field.setAccessible(true);
        field.set(classLoader,this);
    }




    /**
     * 获取要加载的class完整文件名
     * @return
     */

    private String getClassName(String name){

        String clsName = null;

        if (!name.isEmpty()){
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf == -1){
                clsName = name + ".class";
            }
            else {
                clsName = name.substring(lastIndexOf + 1) + ".class";
            }
        }

        return clsName;
    }
}
