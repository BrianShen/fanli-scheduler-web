package com.fanli.scheduler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wei.shen on 2015/8/21.
 */
public class DateUtils {
    public  static Date getCurrentDate() {
        return new Date();
    }

    public static String getCurrentTimeStampStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTimeStamp = simpleDateFormat.format(new Date());
        return nowTimeStamp;
    }
}
