package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.BuildTableSql;
import com.fanli.scheduler.bean.GeneralTable;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.bean.TableMeta;
import com.fanli.scheduler.exception.BuildTableException;
import com.fanli.scheduler.service.SSHService;
import com.fanli.scheduler.service.TableService;
import com.fanli.scheduler.utils.PropertiesUtil;
import com.jcraft.jsch.JSchException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by wei.shen on 2015/7/17.
 */

@Controller
@RequestMapping("/table")
public class TableController {

    private static Logger logger = Logger.getLogger(TableController.class);
    @Autowired
    private SSHService sshService;
    @Autowired
    private TableService tableService;
//    @ResponseBody
//    @RequestMapping(value = "/sql",method = RequestMethod.GET)
//    public Result<String> getBuildTableSql(@RequestBody TableMeta tableMeta) {
//        Result<String> result = new Result<String>();
//        String sql = tableService.getSql(tableMeta);
//        result.setResult(sql);
//        result.setIsSuccess(true);
//        return result;
//    }

    @ResponseBody
    @RequestMapping(value = "/build",method = RequestMethod.POST)
    public Map<String,Object> buildTableOnline(@RequestBody BuildTableSql buildTableSql){
        Map<String,Object> map = null;
        try {
//            boolean ret = sshService.isRunSSHCommandSuccessful("sh /home/hadoop/scheduler-core/bin/hive_command.sh " + "\"" + buildTableSql.getSql() + "\"");
            map = sshService.runSSHCommandWithLogAndResult("hive -e " + "\"" +  buildTableSql.getSql() + "\"");
//            if (!ret) throw new BuildTableException("create table ssh process failed!");

        } catch (JSchException e) {
            logger.error(e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }

//        result.setIsSuccess(tableService.buildTable(sql));
        return map;
    }

    @RequestMapping(value = "/hiveCommand",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> executeHiveCommand(@RequestParam("path") String path) {
        Map<String,Object> map = null;
        Properties p = PropertiesUtil.getProperties("/common.properties");
        String dolBaseDir = p.getProperty("DolBaseDir");
        if (path.charAt(0) != '/') path = "/" + path;
        try {
            logger.info("hive operation:" + "hive -f " + dolBaseDir + path);
            map = sshService.runSSHCommandWithLogAndResult("hive -f " + dolBaseDir + path);
        } catch (JSchException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        };
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/buildTableSql",method = RequestMethod.POST)
    public Result<String> getBuildSql(@RequestBody GeneralTable generalTable) {
        Result<String> result = new Result<String>();
        String sql = tableService.getSql(generalTable);
        result.setResult(sql);
        result.setIsSuccess(true);
        return result;
    }


}
