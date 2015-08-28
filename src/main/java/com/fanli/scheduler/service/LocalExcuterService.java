package com.fanli.scheduler.service;

import java.io.IOException;


/**
 * Created by wei.shen on 2015/8/28.
 */
public class LocalExcuterService {
    public static int executeCMD(final String[] cmdStrArr)
    {
        Runtime rt = Runtime.getRuntime();
        int exitVal = -99;
        Process p = null;
        try {
            p = rt.exec(cmdStrArr);
            exitVal = p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return exitVal;
    }

//    public static void main(String[] args) {
//        String [] cmd = new String[1];
//        cmd[0] = "scp hadoop@192.168.3.227:/home/hadoop/dol/tmp_scheduler_test_sw.dol /home/hadoop/fanli-scheduler/dol_temp";
//        System.out.println(executeCMD(cmd));
//    }




}
