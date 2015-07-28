package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.bean.TableMeta;
import com.fanli.scheduler.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wei.shen on 2015/7/17.
 */

@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private TableService tableService;
    @ResponseBody
    @RequestMapping(value = "/sql",method = RequestMethod.GET)
    public Result<String> getBuildTableSql(@RequestBody TableMeta tableMeta) {
        Result<String> result = new Result<String>();
        String sql = tableService.getSql(tableMeta);
        result.setResult(sql);
        result.setIsSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/build",method = RequestMethod.POST)
    public Result buildTableOnline(@RequestParam("sql") String sql) {
        Result result = new Result();
        result.setIsSuccess(tableService.buildTable(sql));
        return result;
    }


}
