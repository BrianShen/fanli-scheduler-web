package com.fanli.scheduler.controller;

import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.service.TaskConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wei.shen on 2015/7/15.
 */
@Controller
@RequestMapping("/taskManager")
public class TaskHandleController {
    @Autowired
    private TaskConfigService taskConfigService;

    @RequestMapping(value = "/taskConfAdd" ,method = RequestMethod.POST)
    @ResponseBody
    public Result saveTaskConfig(@RequestBody EtlTaskCfg etlTaskCfg) {
        Result result = new Result();
        taskConfigService.insertTaskConfig(etlTaskCfg);
        result.setIsSuccess(true);
        return result;

    }

    @RequestMapping(value = "/queryTasks")
    public Result<EtlTaskCfg> getTaskById(@RequestParam(value = "group", defaultValue = "") String group,
                                          @RequestParam(value = "developer", defaultValue = "") String developer,
                                          @RequestParam(value = "id", defaultValue = "") String id) {
        Result<EtlTaskCfg> result = new Result<EtlTaskCfg>();
        EtlTaskCfg etlTaskCfg = taskConfigService.getTaskById(id);
        if(etlTaskCfg != null) {
            result.setIsSuccess(true);
            result.setResult(etlTaskCfg);
        }else {
            result.setIsSuccess(false);
            result.setMessages("id not found");
        }
        return result;
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getTest() {
        Result<String> r = new Result<String>();
        r.setIsSuccess(true);
        r.setResult("hello");
        return r;
    }

}
