package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.MdsItemtableWithBLOBs;
import com.fanli.scheduler.service.MetaDataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wei.shen on 2015/7/24.
 */

@Controller
@RequestMapping("/mdm")
public class MdmController {
    @Autowired
    private MetaDataManagerService metaDataManagerService;
    @ResponseBody
    @RequestMapping(value = "/saveMeta",method = RequestMethod.POST)
    public Result insertTableMeta(@RequestBody MdsItemtableWithBLOBs mdsItemtableWithBLOBs) {
        Result result = new Result();
        metaDataManagerService.addConfigedMeta(mdsItemtableWithBLOBs);
        result.setIsSuccess(true);
        return result;
    }
}
