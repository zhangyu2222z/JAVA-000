package com.zy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangy
 * @version 3.0
 * 自定义类加载器：
 *     自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 * @email: zhangy2222z@sina.cn
 * @create 2020-10-19 15:05
 **/

public class CustomClassLoader extends ClassLoader {
    public static void main (String[] args) throws InvocationTargetException {
        try {
            try {
                Class<?> clazz = new CustomClassLoader().findClass("Hello");
                Object obj = clazz.newInstance();
                Method[] declaredMethods = clazz.getDeclaredMethods();
                if (declaredMethods.length > 0){
                    declaredMethods[0].invoke(obj);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream fis = null;
        try {
            StringBuffer sb = new StringBuffer();
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\zy\\Hello.xlass");
//            File file = new File("resource/Hello.xlass");
            fis = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int read = fis.read(bytes);
            for (int i =0;i<read;i++) {
                bytes[i] = (byte)(255 - bytes[i]);
            }
            Class<?> aClass = defineClass(name, bytes, 0, read);
            return aClass;
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException("目标文件加载失败！");
        } catch (IOException e) {
            throw new ClassNotFoundException("目标文件加载失败！");
        }
    }



}
