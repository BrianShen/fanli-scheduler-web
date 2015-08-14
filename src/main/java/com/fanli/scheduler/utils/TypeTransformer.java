package com.fanli.scheduler.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcfrank1st on 8/13/15.
 */
public class TypeTransformer {
    public static Map<String, String> fromHive = new HashMap<String, String>();
    static {
        fromHive.put("BOOLEAN", "VARCHAR(5)");
        fromHive.put("FLOAT", "VARCHAR(255)");
        fromHive.put("DOUBLE", "VARCHAR(255)");
        fromHive.put("STRING","TEXT");
    }

    public static String transformHive(String type) {
        String val = fromHive.get(type.toUpperCase());
        if (null != val) {
            return val;
        }
        return type;
    }
}
