package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.BuildTableSql;
import com.fanli.scheduler.bean.GeneralTable;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.bean.TableMeta;
import com.fanli.scheduler.exception.BuildTableException;
import com.fanli.scheduler.service.SSHService;
import com.fanli.scheduler.service.TableService;
import com.jcraft.jsch.JSchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by wei.shen on 2015/7/17.
 */

@Controller
@RequestMapping("/table")
public class TableController {
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
    public Result buildTableOnline(@RequestBody BuildTableSql buildTableSql){
        Result result = new Result();
        try {
            boolean ret = sshService.isRunSSHCommandSuccessful("sh /home/hadoop/scheduler-core/bin/hive_command.sh " + "\"" + buildTableSql.getSql() + "\"");
//            if (!ret) throw new BuildTableException("create table ssh process failed!");
            result.setIsSuccess(ret);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        result.setIsSuccess(tableService.buildTable(sql));
        return result;
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
