package com.fanli.scheduler.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcfrank1st on 8/13/15.
 */
public class TypeTransformer {
    public static Map<String, String> fromHive = new HashMap<String, String>();
    public static Map<String, String> toHive = new HashMap<String, String>();
    static {
        fromHive.put("BOOLEAN", "VARCHAR(10)");
        fromHive.put("FLOAT", "VARCHAR(100)");
        fromHive.put("DOUBLE", "VARCHAR(255)");
        fromHive.put("STRING","TEXT");
        fromHive.put("TINYINT","TINYINT");
        fromHive.put("INT","BIGINT");
        fromHive.put("SMALLINT","SMALLINT");
        fromHive.put("BIGINT","BIGINT");
        fromHive.put("DATE","VARCHAR(30)");
        fromHive.put("CHAR","VARCHAR(255)");
        fromHive.put("BINARY","VARBINARY");
        fromHive.put("VARCHAR","VARCHAR(8000)");
        fromHive.put("TIMESTAMP","VARCHAR(255)");

        toHive.put("INT","INT");
        toHive.put("TINYINT","TINYINT");
        toHive.put("SMALLINT","SMALLINT");
        toHive.put("BIGINT","BIGINT");
        toHive.put("INTEGER","INT");
        toHive.put("FLOAT","FLOAT");
        toHive.put("DOUBLE","DOUBLE");
        toHive.put("TIMESTAMP","TIMESTAMP");

    }

    public static String transformHive(String type) {
        String val = fromHive.get(type.toUpperCase());
        if (null != val) {
            return val;
        }
        if (type.toUpperCase().contains("DECIMAL")) {
            return type.toUpperCase();
        }
        return "TEXT";
    }

    public static String transTohive(String type) {
        String val = toHive.get(type.toUpperCase());
        if (null != val) {
            return val.toLowerCase();
        }
        if (type.toUpperCase().contains("DECIMAL")) {
            return type.toLowerCase();
        }
        return "string";
    }
}
