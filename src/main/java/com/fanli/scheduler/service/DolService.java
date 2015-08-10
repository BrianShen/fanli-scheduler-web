package com.fanli.scheduler.service;

import com.fanli.scheduler.utils.PropertiesUtil;
import com.jcraft.jsch.JSchException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by wei.shen on 2015/8/10.
 */
@Service
public class DolService {
    @Autowired
    private SSHService sshService;
    public Boolean importDol(String path) {
        String targetPath = PropertiesUtil.getProperties("common.properties").getProperty("DolTargetDir");
        String command = "cp " + path.trim() + " " + targetPath.trim() + ";";
        Boolean ret = false;
        try {
            ret = sshService.isRunSSHCommandSuccessful(command);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ret);
        return ret;
    }

    public Boolean isExists(String path) {
        String command ="/home/hadoop/scheduler-core/bin/file_exists.sh " + path;
        try {
            return sshService.isRunSSHCommandSuccessful(command);
        } catch (JSchException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
