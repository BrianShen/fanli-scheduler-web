package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.TaskAdder;
import com.fanli.scheduler.bean.TaskQuery;
import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.EtlTaskrelaCfg;
import com.fanli.scheduler.service.TaskConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        Date date = new Date();
        etlTaskCfg.setAddTime(date);
        etlTaskCfg.setUpdateTime(date);
        int ret = taskConfigService.insertTaskConfig(etlTaskCfg);
        result.setResult(etlTaskCfg);
        result.setIsSuccess(true);
        return result;
    }

    @RequestMapping(value = "/taskpreadd",method = RequestMethod.POST)
    @ResponseBody
    public Result savePretask(@RequestBody TaskAdder taskAdder) {
        Result result = new Result();
        String[] pres= taskAdder.getPreId().split(",");
        Integer[] pre = new Integer[pres.length];
        for(int i = 0;i < pres.length;i ++) {
            pre[i] = Integer.parseInt(pres[i]);
        }
        for(int i = 0;i < pre.length;i ++) {
            EtlTaskrelaCfg etlTaskrelaCfg = new EtlTaskrelaCfg();
            etlTaskrelaCfg.setTaskId(Integer.parseInt(taskAdder.getTaskId()));
            etlTaskrelaCfg.setPreId(pre[i]);
            etlTaskrelaCfg.setIfEnable(1);
            etlTaskrelaCfg.setOffset(0);
            Date date = new Date();
            etlTaskrelaCfg.setTimeStamp(date);
            etlTaskrelaCfg.setUpdatetime(date);
            taskConfigService.insertTaskRela(etlTaskrelaCfg);
        }
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
