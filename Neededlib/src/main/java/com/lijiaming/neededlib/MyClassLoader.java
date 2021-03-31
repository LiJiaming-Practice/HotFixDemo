package com.lijiaming.neededlib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader{

    private String myClassPath;    //加载class目录

    public MyClassLoader(String classPath){
        myClassPath = classPath;
    }

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
