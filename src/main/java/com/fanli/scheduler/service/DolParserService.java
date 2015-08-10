package com.fanli.scheduler.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/8/8.
 */
public class DolParserService {
    private static List<String> allTable = new ArrayList<String>();
    private static List<String> sourceTable = new ArrayList<String>();
    private static List<String> tempTable = new ArrayList<String>();
    private static final String REG_FROM = "from[\\s]+([^\\(\\s]+)";
    private static final String REG_JOIN = "join[\\s]+([^\\(\\s]+)";
    private static final String REG_TEMP_TABLE = "create[\\s]+table[\\s]+([^\\s]+)";


    /**
     * @param file
     * @return
     */
    public static List<String> parseSourceTables(File file) {
        if(!isExists(file)) return null;
        List<String> commands =  splitToCommand(getStringDol(file));
        System.out.println(commands);
        allTable = getAllTable(commands);
        tempTable = getTempTable(commands);

//        for (int i = 0;i < sourceTable.size();i ++) {
//            for (int j = 0;j < tempTable.size();j ++) {
//                if (sourceTable.get(i).equals(tempTable.get(j))) sourceTable.remove(i);
//            }
//        }
        if (allTable.removeAll(tempTable)) {
            sourceTable = allTable;
        }
        return sourceTable;
    }

    private static List<String> getAllTable(List<String> commands) {
        List<String> all = new ArrayList<String>();
        for (int i = 0;i < commands.size();i ++) {
            Pattern from = Pattern.compile(REG_FROM,Pattern.CASE_INSENSITIVE);
            Matcher fromMatcher = from.matcher(commands.get(i));
            while (fromMatcher.find()) {
                String str = "";
                System.out.println(fromMatcher.group(1).split("."));
                if (fromMatcher.group(1).split("\\.").length > 1) {
                    str = fromMatcher.group(1).split("\\.")[1];
                } else str = fromMatcher.group(1);

                if (!all.contains(str)) {
                    all.add(str);
                }
            }
            Pattern join = Pattern.compile(REG_JOIN,Pattern.CASE_INSENSITIVE);
            Matcher joinMatcher = join.matcher(commands.get(i));
            while (joinMatcher.find()) {
                String str = "";
                if (joinMatcher.group(1).split("\\.").length > 1) {
                    str = joinMatcher.group(1).split("\\.")[1];
                } else str = joinMatcher.group(1);
                if (!all.contains(str)) {
                    all.add(str);
                }
            }
        }
        return all;
    }

    private static List<String> getTempTable(List<String> commands) {
        List<String> temp = new ArrayList<String>();
        for(int i = 0;i < commands.size();i ++) {
            Pattern tmp = Pattern.compile(REG_TEMP_TABLE,Pattern.CASE_INSENSITIVE);
            Matcher matcher = tmp.matcher(commands.get(i));
            while (matcher.find()) {
                String str = "";
                if (matcher.group(1).split("\\.").length > 1) {
                    str = matcher.group(1).split("\\.")[1];
                } else str = matcher.group(1);
                if (!temp.contains(str))
                temp.add(str);
            }
        }
        return temp;
    }


    /**
     * @param sql
     * @return
     */
    private static List<String> splitToCommand(String sql) {
        String[] arr = sql.split(";");
        for (int i = 0;i < arr.length;i ++) {
            arr[i] = arr[i].trim();
        }
        return Arrays.asList(arr);
    }

    private static String getStringDol(File file)  {
        InputStreamReader inputStreamReader = null;
        StringBuffer sb = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(file),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            sb = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + " ");
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sb.toString();

    }

    private static Boolean isExists(File file) {
        if (!file.exists()) System.out.println("The file is not exists");
        if (!file.isFile()) System.out.println("This is not a file");
        return file.exists()&& file.isFile();
    }

    public static void main(String[] args) {
        String path = "D:" + File.separator + "DW_UserDB.Fact_User.job.sql";
        File file = new File(path);
        System.out.println(DolParserService.parseSourceTables(file));


//        Pattern pattern = Pattern.compile("b+g");
//        Matcher matcher = pattern.matcher("bbbg");
//        System.out.println(matcher.matches());
    }

}
