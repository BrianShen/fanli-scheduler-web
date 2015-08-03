package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.DimDateDeveloper;
import com.fanli.scheduler.entity.DimTargetHiveDb;
import com.fanli.scheduler.service.DimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wei.shen on 2015/7/31.
 */

@Controller
@RequestMapping("/dim")
public class DimentionController {

    @Autowired
    private DimService dimService;

    @ResponseBody
    @RequestMapping(value = "/developers",method = RequestMethod.GET)
    public Result<DimDateDeveloper> getDevelopers() {
        Result<DimDateDeveloper> result = new Result<DimDateDeveloper>();
        List<DimDateDeveloper> list =  dimService.getAllDevelopers();
        result.setResults(list);
        result.setIsSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/targetDBs",method = RequestMethod.GET)
    public Result<DimTargetHiveDb> getTargetDatabases() {
        Result<DimTargetHiveDb> result = new Result<DimTargetHiveDb>();
        result.setResults(dimService.getAllTargetDbs());
        result.setIsSuccess(true);
        return result;
    }


}
