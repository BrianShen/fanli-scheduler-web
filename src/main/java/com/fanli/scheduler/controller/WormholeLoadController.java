package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.service.EtlLoadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wei.shen on 2015/9/14.
 */
@Controller
@RequestMapping("/load")
public class WormholeLoadController {
    @Autowired
    private EtlLoadService etlLoadService;

    private static Logger logger = Logger.getLogger(WormholeLoadController.class);

    @ResponseBody
    @RequestMapping(value = "/sql",method = RequestMethod.GET)
    public Result<String> getTransportSql(@RequestParam Integer taskid) {
        Result<String> result = new Result<String>();
        if (taskid == null) {
            logger.error("request wormhole load config ,taskid can not be null");
            return null;
        }
        result.setResult(etlLoadService.getReaderCfg(taskid));
        result.setIsSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sql",method = RequestMethod.POST)
    public Result updateTransferSql(@RequestParam("taskid") Integer taskid,@RequestParam("paramMap") String paramMap) {
        Result result = new Result();
        int row = etlLoadService.modifyTransferSql(taskid,paramMap);
        if (row == 1) {
            result.setIsSuccess(true);
        } else result.setIsSuccess(false);
        return result;
    }
}
