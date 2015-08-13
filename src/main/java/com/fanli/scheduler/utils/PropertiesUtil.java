package com.fanli.scheduler.utils;



import java.io.*;
import java.util.Properties;

/**
 * Created by wei.shen on 2015/8/10.
 */
public class PropertiesUtil {


    public static void addProperties(String key[], String value[], String file) {
        Properties iniFile = getProperties(file);
        FileOutputStream oFile = null;
        try {
            iniFile.put(key, value);
            oFile = new FileOutputStream(file, true);
            iniFile.store(oFile, "modify properties file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oFile != null) {
                    oFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static Properties getProperties(String file) {
        Properties pro = null;
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(file);
        try {
            pro = new Properties();
            pro.load(in);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pro;
    }

    /**
     * 保存属性到文件中
     *
     * @param pro
     * @param file
     */
    public static void saveProperties(Properties pro, String file) {
        if (pro == null) {
            return;
        }
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(file, false);
            pro.store(oFile, "modify properties file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oFile != null) {
                    oFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改属性文件
     *
     * @param key
     * @param value
     */
    public static void updateProperties(String key, String value, String file) {
        // key为空则返回
        if (key == null || "".equalsIgnoreCase(key)) {
            return;
        }
        Properties pro = getProperties(file);
        if (pro == null) {
            pro = new Properties();
        }
        pro.put(key, value);

        // 保存属性到文件中
        saveProperties(pro, file);
    }

    public static void main(String[] args) {
        //updateProperties("key", "value", "D:/test.properties");
        //System.out.println(getProperties("common.properties").get("DolBaseDir"));
        InputStream in = ClassLoader.getSystemResourceAsStream("common.properties");
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(p.getProperty("DolBaseDir"));
    }
}
