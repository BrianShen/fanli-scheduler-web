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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.SQLException;
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
//    @Autowired
//    private JdbcTemplate hiveJdbcTemplate;
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
//        try {
            //String command = "sh /home/hadoop/ETL_BIN/hive_command.sh " + "\"" + buildTableSql.getSql() + "\"";
            //logger.info("build table sql:" + command);
            //boolean ret = sshService.isRunSSHCommandSuccessful(command);
             //result.setIsSuccess(sshService.isRunSSHCommandSuccessful("hive -e " + "\"" +  buildTableSql.getSql() + "\""));
//            if (!ret) throw new BuildTableException("create table ssh process failed!");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:hive2://192.168.3.172:10000/default");
        dataSource.setUsername("hadoop");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
        JdbcTemplate hiveJdbcTemplate = new JdbcTemplate();
        hiveJdbcTemplate.setDataSource(dataSource);
        //hiveJdbcTemplate.execute(buildTableSql.getSql());
        String [] cmds = buildTableSql.getSql().split(";");
        for (String cmd:cmds) {
            hiveJdbcTemplate.execute(cmd);
        }
        //hiveJdbcTemplate.execute(buildTableSql.getSql());
            result.setIsSuccess(true);
//        } catch (JSchException e) {
//            logger.error(e.getLocalizedMessage());
//        } catch (IOException e) {
//            logger.error(e.getLocalizedMessage());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        // result.setIsSuccess(ret);
//        result.setIsSuccess(tableService.buildTable(sql));
        return result;
    }

    @RequestMapping(value = "/hiveCommand",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> executeHiveCommand(@RequestParam("path") String path) {
        //Map<String,Object> map = null;
        Result<String> result = new Result<String>();
        Properties p = PropertiesUtil.getProperties("/common.properties");
        String dolBaseDir = p.getProperty("DolBaseDir");
        if (path.charAt(0) != '/') path = "/" + path;
        //logger.info("hive operation:" + "hive -f " +"\"" + dolBaseDir + path +"\"");
        String command = "sh /home/hadoop/ETL_BIN/hive_command.sh " + "\""+dolBaseDir + path + "\"";
        logger.info(command);
        //String command = "cat " + dolBaseDir + path;
        //String log = sshService.runSSHCommand("hive -f " +"\"" + dolBaseDir + path +"\"");
        try {
            boolean ret = sshService.isRunSSHCommandSuccessful(command);
            result.setIsSuccess(ret);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
