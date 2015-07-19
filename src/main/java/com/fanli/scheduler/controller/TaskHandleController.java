package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.TaskQuery;
import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.service.TaskConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/queryTasks",method = RequestMethod.GET)
    @ResponseBody
    public Result<EtlTaskCfg> getTaskByParams(@RequestParam(value = "taskId",defaultValue = "") Integer taskId,@RequestParam(value = "taskGroupId",defaultValue = "") Integer taskGroupId,@RequestParam(value = "owner",defaultValue = "") String owner) {
        Result<EtlTaskCfg> result = new Result<EtlTaskCfg>();
        TaskQuery taskQuery = new TaskQuery();
        taskQuery.setOwner(owner);
        taskQuery.setTaskGroupId(taskGroupId);
        taskQuery.setTaskId(taskId);
        System.out.println(taskQuery);
        List<EtlTaskCfg> etlTaskCfgs = taskConfigService.getTaskByParams(taskQuery);
        if (etlTaskCfgs != null) {
            result.setIsSuccess(true);
            result.setResults(etlTaskCfgs);
            result.setMessages("获取任务成功");
        } else {
            result.setIsSuccess(false);
        }

        //EtlTaskCfg etlTaskCfg = taskConfigService.getTaskById(id);
//        if(etlTaskCfg != null) {
//            result.setIsSuccess(true);
//            result.setResult(etlTaskCfg);
//        }else {
//            result.setIsSuccess(false);
//            result.setMessages("id not found");
//        }
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
