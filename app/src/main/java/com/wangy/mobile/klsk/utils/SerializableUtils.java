package com.wangy.mobile.klsk.utils;

import com.wangy.mobile.klsk.application.HApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import io.reactivex.Observable;

/**
 * Created by xiongxiong on 2018/7/16.
 */

public class SerializableUtils {

    public static String appMoreCachefile = HApplication.getInstances().getCacheDir()+"/appMore.dat";
    public static String appHomeCachefile = HApplication.getInstances().getCacheDir()+"/appHome.dat";
    //把序列化的类写到sdcard文件里
    public static <T extends Serializable> void setAppMoreCache(T t){

        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectOutputStream oos = null;
                try {
                    File file = new File(appMoreCachefile);
                    if(file.exists()){
                        file.delete();
                    }
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }

                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(t);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            /**
             * 把文件转化成序列化的类
             * @param
             * @return
             * @throws Exception
             */

         }).start();
    }


    public static <T extends Serializable> void setAppHomeCache(T t){

        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectOutputStream oos = null;
                try {
                    File file = new File(appHomeCachefile);
                    if(file.exists()){
                        file.delete();
                    }
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }

                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(t);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            /**
             * 把文件转化成序列化的类
             * @param
             * @return
             * @throws Exception
             */

        }).start();
    }
    public static Serializable getAppMoreCache(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(appMoreCachefile));
            Object object = ois.readObject();

            if (object != null) {
                return (Serializable) object;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Serializable getAppHomeCache(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(appHomeCachefile));
            Object object = ois.readObject();

            if (object != null) {
                return (Serializable) object;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static void deleteCacheFile(){
        File file1 = new File(appMoreCachefile);
        if(file1.exists()){
            file1.delete();
        }

        File file2 = new File(appHomeCachefile);
        if(file2.exists()){
            file2.delete();
        }
    }
}
