package com.fanli.scheduler.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcfrank1st on 8/13/15.
 */
public class TypeTransformer {
    public static Map<String, String> fromHive = new HashMap<String, String>();
    static {
        fromHive.put("BOOLEAN", "VARCHAR(10)");
        fromHive.put("FLOAT", "VARCHAR(100)");
        fromHive.put("DOUBLE", "VARCHAR(255)");
        fromHive.put("STRING","TEXT");
        fromHive.put("TINYINT","TINYINT");
        fromHive.put("INT","INT");
        fromHive.put("SMALLINT","SMALLINT");
        fromHive.put("BIGINT","BIGINT");
        fromHive.put("DATE","VARCHAR(30)");
        fromHive.put("CHAR","VARCHAR(255)");
        fromHive.put("BINARY","VARBINARY");
        fromHive.put("VARCHAR","VARCHAR(65535)");
        fromHive.put("TIMESTAMP","VARCHAR(255)");
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
}
