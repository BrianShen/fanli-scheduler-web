package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.GeneralTable;
import com.fanli.scheduler.bean.JdbcBuildTable;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wei.shen on 2015/8/11.
 */
@Controller
@RequestMapping("/db")
public class DatebaseController {
    @Autowired
    private DatabaseService databaseService;


    @ResponseBody
    @RequestMapping(value = "/tables",method = RequestMethod.GET)
    public Result<String> getTables(@RequestParam("connectProp")String connectProp) {
        Result<String> result = new Result<String>();
        if (databaseService.getTablesByConnectionProp(connectProp)!= null) {
            result.setResults(databaseService.getTablesByConnectionProp(connectProp));
            result.setIsSuccess(true);
        } else result.setIsSuccess(false);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/tableExists",method = RequestMethod.GET)
    public Result tableExists(@RequestParam("connectProp")String connectProp,@RequestParam("db")String db,@RequestParam("table")String table) {
        Result result = new Result();
        result.setIsSuccess(databaseService.checkTableExists(connectProp,db,table));
        return  result;
    }

    @ResponseBody
    @RequestMapping(value = "/tableInfo",method = RequestMethod.GET)
    public Result<String> getTableByName(@RequestParam("connectProp")String connectProp,@RequestParam("db")String db,@RequestParam("tableName") String tableName) {
        Result<String> result = new Result<String>();
        result.setResults(databaseService.getTableInfo(connectProp,db,tableName));
        result.setIsSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/incrField",method = RequestMethod.GET)
    public Result<GeneralTable> getJdbcIncreaseField(@RequestParam("connectProp")String connectProp,@RequestParam("db")String db,@RequestParam("tableName") String tableName) {
        Result<GeneralTable> result = new Result<GeneralTable>();
        result.setResult(databaseService.getDateTimeIncreaseField(connectProp, db, tableName));
        result.setIsSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/columns",method = RequestMethod.GET)
    public Result<GeneralTable> getTableColumns(@RequestParam("connectProp")String connectProp,@RequestParam("db")String db,@RequestParam("tableName") String tableName) {
        Result<GeneralTable> result = new Result<GeneralTable>();
        result.setResult(databaseService.getTableDetail(connectProp, db, tableName));
        result.setIsSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/buildTables",method = RequestMethod.POST)
    public Result buildJdbcTable(@RequestBody JdbcBuildTable jdbcBuildTable) {
        Result result = new Result();
        int ret = databaseService.build(jdbcBuildTable.getConnectProp(),jdbcBuildTable.getDb(),jdbcBuildTable.getSql());
        if (ret == 0) {
            result.setIsSuccess(true);
        }else result.setIsSuccess(false);
        return result;
    }


}
