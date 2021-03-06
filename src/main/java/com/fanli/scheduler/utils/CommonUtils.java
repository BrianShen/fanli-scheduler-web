package com.fanli.scheduler.utils;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class CommonUtils {

    private static Logger logger = Logger.getLogger(CommonUtils.class);

    public static int getDayDiff(Date date0, Date date1) {
        final int DAY_TO_MS = 1000 * 3600 * 24;
        int diff = (int) Math.floor(date0.getTime() - date1.getTime()) / DAY_TO_MS;
        return diff;
    }

    public static String getThisDay(Date d) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(d);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static String getCurrentTimeStampStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTimeStamp = simpleDateFormat.format(new Date());
        return nowTimeStamp;
    }

    public static String getCurrentDateStr() {
        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return date;
    }

    public static Date strToDate(String strDate) {
        if (strDate.length() == 10)
            return strToDate(strDate, "yyyy-MM-dd");
        if (strDate.length() == 7)
            return strToDate(strDate, "yyyy-MM");
        else
            return strToDate(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat simpleDateFormat;
        Date date;
        try {
            simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static String dateToStr(Date date, String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.format(date);
    }

    public static String generateInstanceId(String taskId,String type,Date initDate) {
        if(type.equals("H")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            return taskId + sdf.format(initDate);
        }else if(type.equals("D") ){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd00");
            return taskId + sdf.format(initDate);
        }else if(type.equals("W")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMWW00");
            return taskId + sdf.format(initDate);
        }else if(type.equals("M")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM0100");
            return taskId + sdf.format(initDate);
        }else if(type.equals("mi")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            return taskId + sdf.format(initDate);
        }else{
            throw new NullPointerException("error input cycle type "+type);
        }
    }

    public static String Date2String(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmssSSS");
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return sdf.format(c.getTime());
    }

    public static String getCalDt(String time_id,String offset) throws ParseException{
            String type1 = new String(offset);
            String type2 = type1.substring(0, 1);
            int gap = new Integer(type1.substring(1,type1.length()));
            if(gap>=1000){
                logger.error("the cal_dt is more than 1000");
                return null;
            }
            return CommonUtils.getAppointDay(time_id, type2, gap);

    }

    public static String getDay8(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        return sdf.format(c.getTime());
    }

    public static String getDay10(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(d);
    }

    private static String getAppointDay(String time_id,String type,int gap) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = sdf.parse(time_id);
        c.setTime(date);
        //c.add(Calendar.DAY_OF_MONTH, -1);
        if(type.equals("D")){
            c.add(Calendar.DAY_OF_MONTH, -gap);
            return sdf.format(c.getTime());
        }else if(type.equals("M")){
            c.add(Calendar.MONDAY, -gap);
            return sdf.format(c.getTime());
        }else if(type.equals("H")){
            c.add(Calendar.HOUR, -gap);
            return sdf.format(c.getTime());
        }else{
            return null;
        }
    }

    public static String getLastDay10(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(new Date(d.getTime() - 86400 * 1000));
    }

    public static String getLastHour(Date d) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.HOUR,-1);
            return sdf.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static String getThisDay8(Date d) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getThisDay10(Date d) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String CaldtReplace(String para,String offset,Date init_date)
    {
        String cal_dt = null;
        try{
            cal_dt = CommonUtils.getCalDt(CommonUtils.getLastDay10(init_date), offset);
        }catch (ParseException e){
            logger.error(e.getMessage(), e);
            return null;
        }

        if(para == null || para.trim().equals("")){
            return null;
        }

        String ncal_dt = CommonUtils.getNCal_dt(cal_dt, "${ncal_dt}");
        String cal_dt8 = CommonUtils.getCal_dt8(cal_dt, "${cal_dt8}");
        String ncal_dt8 = CommonUtils.getNCal_dt8(cal_dt, "${ncal_dt8}");

        String last_week8 = CommonUtils.getLastWeek8(cal_dt, "${last_week8}");
        String last_week10 = CommonUtils.getLastWeek10(cal_dt, "${last_week10}");

        String end_day_last_month8 = CommonUtils.getEndDayThisMonth8(cal_dt, "${end_day_this_month8}");
        String end_day_last_month10 = CommonUtils.getEndDayThisMonth10(cal_dt, "${end_day_this_month10}");

        String mon_next_week8 = CommonUtils.getMonNextWeek8(cal_dt, "${monday_next_week8}");
        String mon_next_week10 = CommonUtils.getMonNextWeek10(cal_dt, "${monday_next_week10}");

        String first_day_this_month8 = CommonUtils.getFirstDayThisMonth8(cal_dt, "${first_day_this_month8}");
        String first_day_this_month10 = CommonUtils.getFirstDayThisMonth10(cal_dt, "${first_day_this_month10}");

        String first_day_last_month8 = CommonUtils.getFirstDayLastMonth8(cal_dt, "${first_day_last_month8}");
        String first_day_last_month10 = CommonUtils.getFirstDayLastMonth10(cal_dt, "${first_day_last_month10}");

        String last_day_last_month8 =  CommonUtils.getLastDayLastMonth8(cal_dt, "${last_day_last_month8}");
        String last_day_last_month10 = CommonUtils.getLastDayLastMonth10(cal_dt, "${last_day_last_month10}");

        String this_hour = CommonUtils.getThisHour(init_date);
        String last_hour = CommonUtils.getLastHour(init_date);
        String this_day8 = CommonUtils.getThisDay8(init_date);
        String this_day10 = CommonUtils.getThisDay10(init_date);

        String Ndays_cal_dt = CommonUtils.getNdays_cal_dt(cal_dt,"${30days_cal_dt}");

        String unix_timestamp = new Long(init_date.getTime()/1000).toString();

        return  para.replace("${cal_dt}", cal_dt)
                .replace("${ncal_dt}", ncal_dt)
                .replace("${cal_dt8}", cal_dt8)
                .replace("${ncal_dt8}", ncal_dt8)
                .replace("${last_week8}", last_week8)
                .replace("${last_week10}", last_week10)
                .replace("${monday_next_week8}", mon_next_week8)
                .replace("${monday_next_week10}", mon_next_week10)
                .replace("${end_day_this_month8}", end_day_last_month8)
                .replace("${end_day_this_month10}", end_day_last_month10)
                .replace("${first_day_this_month8}", first_day_this_month8)
                .replace("${first_day_this_month10}", first_day_this_month10)
                .replace("${first_day_last_month8}", first_day_last_month8)
                .replace("${first_day_last_month10}", first_day_last_month10)
                .replace("${last_day_last_month8}", last_day_last_month8)
                .replace("${last_day_last_month10}", last_day_last_month10)
                .replace("${this_hour}", this_hour)
                .replace("${last_hour}", last_hour)
                .replace("${this_day8}", this_day8)
                .replace("${this_day10}", this_day10)
                .replace("${30days_cal_dt}", Ndays_cal_dt)
                .replace("${unix_timestamp}",unix_timestamp)
                .replace("${date}",cal_dt);
    }

    private static String getNCal_dt(String cal_dt,String pattern){

        if(pattern.equals("${ncal_dt}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, 1);
                return sdf.format(c.getTime());
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }


    private static String getEndDayThisMonth8(String cal_dt,String pattern){
        if(pattern.equals("${end_day_this_month8}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.MONTH, 1);
                c.set(Calendar.DAY_OF_MONTH, 1);
                c.add(Calendar.DAY_OF_MONTH, -1);
                return sdf.format(c.getTime()).replace("-", "");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getEndDayThisMonth10(String cal_dt,String pattern){
        if(pattern.equals("${end_day_this_month10}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.MONTH, 1);
                c.set(Calendar.DAY_OF_MONTH, 1);
                c.add(Calendar.DAY_OF_MONTH, -1);
                return sdf.format(c.getTime());
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getLastWeek8(String cal_dt,String pattern){
        if(pattern.equals("${last_week8}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, -6);
                return sdf.format(c.getTime()).replace("-", "");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getLastWeek10(String cal_dt,String pattern){
        if(pattern.equals("${last_week10}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, -6);
                return sdf.format(c.getTime());
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getFirstDayThisMonth10(String cal_dt,String pattern){
        if(pattern.equals("${first_day_this_month10}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.set(Calendar.DAY_OF_MONTH, 1);
                return sdf.format(c.getTime());
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getCal_dt8(String cal_dt,String pattern){
        if(pattern.equals("${cal_dt8}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                return sdf.format(c.getTime()).replace("-", "");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getNCal_dt8(String cal_dt,String pattern){
        if(pattern.equals("${ncal_dt8}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, 1);
                return sdf.format(c.getTime()).replace("-", "");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getFirstDayThisMonth8(String cal_dt,String pattern){
        if(pattern.equals("${first_day_this_month8}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.set(Calendar.DAY_OF_MONTH, 1);
                return sdf.format(c.getTime()).replace("-", "");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getFirstDayLastMonth8(String cal_dt,String pattern){
        if(pattern.equals("${first_day_last_month8}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.MONTH, -1);
                c.set(Calendar.DAY_OF_MONTH, 1);
                return sdf.format(c.getTime()).replace("-", "");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getFirstDayLastMonth10(String cal_dt,String pattern){
        if(pattern.equals("${first_day_last_month10}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.MONTH, -1);
                c.set(Calendar.DAY_OF_MONTH, 1);
                return sdf.format(c.getTime());
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getNdays_cal_dt(String cal_dt,String pattern){
        //if(pattern.equals("${Ndays_cal_dt}")){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date date = sdf.parse(cal_dt);
            c.setTime(date);
            String s = pattern.replace("${", "").replace("days_cal_dt}", "");
            Integer interval = new Integer(pattern.replace("${", "").replace("days_cal_dt}", ""));
            c.add(Calendar.DAY_OF_MONTH, -interval);
            return sdf.format(c.getTime());
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
        //}
        //return null;
    }

    private static String getLastDayLastMonth8(String cal_dt,String pattern){
        if(pattern.equals("${last_day_last_month8}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.set(Calendar.DAY_OF_MONTH, 1);
                c.add(Calendar.DATE, -1);
                return sdf.format(c.getTime()).replace("-", "");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getLastDayLastMonth10(String cal_dt,String pattern){
        if(pattern.equals("${last_day_last_month10}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.set(Calendar.DAY_OF_MONTH, 1);
                c.add(Calendar.DATE, -1);
                return sdf.format(c.getTime());
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getThisHour(Date d){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
            return sdf.format(d);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }


    }


    private static String getMonNextWeek8(String cal_dt,String pattern){
        if(pattern.equals("${monday_next_week8}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.WEEK_OF_YEAR, 1);
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                return sdf.format(c.getTime()).replace("-", "");
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private static String getMonNextWeek10(String cal_dt,String pattern){
        if(pattern.equals("${monday_next_week10}")){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Date date = sdf.parse(cal_dt);
                c.setTime(date);
                c.add(Calendar.WEEK_OF_YEAR, 1);
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                return sdf.format(c.getTime());
            }catch(Exception e){
                logger.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }
}
