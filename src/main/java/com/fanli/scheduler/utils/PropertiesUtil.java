package com.fanli.scheduler.utils;



import java.io.*;
import java.util.Properties;

/**
 * Created by wei.shen on 2015/8/10.
 */
public class PropertiesUtil {

    /**
     * ���������ļ�ֵ
     *
     * @param key
     * @param value
     */
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

    /**
     * ��ȡ�����ļ�
     *
     * @return
     */
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
     * �������Ե��ļ���
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
     * �޸������ļ�
     *
     * @param key
     * @param value
     */
    public static void updateProperties(String key, String value, String file) {
        // keyΪ���򷵻�
        if (key == null || "".equalsIgnoreCase(key)) {
            return;
        }
        Properties pro = getProperties(file);
        if (pro == null) {
            pro = new Properties();
        }
        pro.put(key, value);

        // �������Ե��ļ���
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
