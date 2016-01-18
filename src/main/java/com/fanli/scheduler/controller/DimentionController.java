package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.CtlOwnerInfo;
import com.fanli.scheduler.entity.DimDateDeveloper;
import com.fanli.scheduler.entity.DimTargetHiveDb;
import com.fanli.scheduler.entity.EtlSourceDim;
import com.fanli.scheduler.service.DimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Result<CtlOwnerInfo> getDevelopers() {
        Result<CtlOwnerInfo> result = new Result<CtlOwnerInfo>();
        List<CtlOwnerInfo> list =  dimService.getAllDevelopers();
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

    @ResponseBody
    @RequestMapping(value = "/dbSource",method = RequestMethod.GET)
    public Result<EtlSourceDim> getSourceByType(@RequestParam("type") String type) {
        Result<EtlSourceDim> result = new Result<EtlSourceDim>();
        result.setResults(dimService.getSourceByType(type));
        result.setIsSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/domain",method = RequestMethod.GET)
    public Result<String> getDomainByConnectionProperty(@RequestParam("prop")String prop) {
        Result<String> result = new Result<String>();
        result.setResult(dimService.getDomainByConnectionProperty(prop));
        result.setIsSuccess(true);
        return result;
    }


}
