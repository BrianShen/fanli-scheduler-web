package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.TaskAdder;
import com.fanli.scheduler.bean.TaskQuery;
import com.fanli.scheduler.entity.EtlLoadCfg;
import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.EtlTaskrelaCfg;
import com.fanli.scheduler.entity.EtlTaskrelaCfgExample;
import com.fanli.scheduler.mapping.EtlLoadCfgMapper;
import com.fanli.scheduler.mapping.EtlTaskCfgMapper;
import com.fanli.scheduler.mapping.EtlTaskrelaCfgMapper;
import com.fanli.scheduler.service.TaskConfigService;
import com.fanli.scheduler.service.TaskRelaService;
import com.fanli.scheduler.service.TaskService;
import com.fanli.scheduler.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wei.shen on 2015/7/15.
 */
@Controller
@RequestMapping("/taskManager")
public class TaskHandleController {

    private static Logger logger = Logger.getLogger(TaskHandleController.class);
    @Autowired
    private TaskConfigService taskConfigService;

    @Autowired
    private EtlTaskrelaCfgMapper etlTaskrelaCfgMapper;

    @Autowired
    private EtlTaskCfgMapper etlTaskCfgMapper;

    @Autowired
    private EtlLoadCfgMapper etlLoadCfgMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRelaService taskRelaService;

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

    @RequestMapping(value = "/updateTaskRela",method = RequestMethod.POST)
    @ResponseBody
    public Result editPre(@RequestBody TaskAdder taskAdder) {
        taskConfigService.deleteTaskRela(Integer.parseInt(taskAdder.getTaskId()));
        Result result = new Result();
        String[] pres= null;
        if (StringUtils.hasLength(taskAdder.getPreId())){
            pres= taskAdder.getPreId().split(",");
        } else pres = new String[0];

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
    public Result<EtlTaskCfg> getTaskByParams(@RequestParam(value = "taskId",defaultValue = "") Integer taskId,
                                              @RequestParam(value = "taskGroupId",defaultValue = "") Integer taskGroupId,
                                              @RequestParam(value = "owner",defaultValue = "") String owner,
                                              @RequestParam(value = "isValid",defaultValue = "")Integer isValid) {
        Result<EtlTaskCfg> result = new Result<EtlTaskCfg>();
        TaskQuery taskQuery = new TaskQuery();
        taskQuery.setOwner(owner);
        taskQuery.setTaskGroupId(taskGroupId);
        taskQuery.setTaskId(taskId);
        taskQuery.setIsValid(isValid);
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
    @ResponseBody
    @RequestMapping(value = "/task",method = RequestMethod.GET)
    public Result<EtlTaskCfg> getTaskCfgById(@RequestParam("taskid") Integer taskid) {
        Result<EtlTaskCfg> result = new Result<EtlTaskCfg>();
        EtlTaskCfg etlTaskCfg = taskConfigService.getTaskById(taskid);
        result.setResult(etlTaskCfg);
        result.setIsSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/updateTask",method = RequestMethod.POST)
    public Result updateTaskCfg(@RequestBody EtlTaskCfg etlTaskCfg) {
        Result result = new Result();
        int ret = taskConfigService.updateTask(etlTaskCfg);
        if (ret > 0) {
            result.setIsSuccess(true);
        } else result.setIsSuccess(false);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/queryPre",method = RequestMethod.GET)
    public Result<EtlTaskCfg> getPreTasks(@RequestParam("taskid") Integer taskid) {
        Result<EtlTaskCfg> result = new Result<EtlTaskCfg>();
        EtlTaskrelaCfgExample etlTaskrelaCfgExample = new EtlTaskrelaCfgExample();
        EtlTaskrelaCfgExample.Criteria criteria = etlTaskrelaCfgExample.createCriteria();
        if (taskid != null) criteria.andTaskIdEqualTo(taskid);
        List<EtlTaskrelaCfg> list =  etlTaskrelaCfgMapper.selectByExample(etlTaskrelaCfgExample);
        List<EtlTaskCfg> ret = new ArrayList<EtlTaskCfg>();
        for(int i = 0;i < list.size();i ++) {
            ret.add(etlTaskCfgMapper.selectByPrimaryKey(list.get(i).getPreId()));
        }
        result.setResults(ret);
        result.setIsSuccess(true);
        return result;
    }

    @RequestMapping(value = "/transferTaskAdd",method = RequestMethod.POST)
    @ResponseBody
    public Result saveTransferTask(@RequestBody EtlTaskCfg etlTaskCfg) {
        Result result = new Result();
        Date date = new Date();
        etlTaskCfg.setAddTime(date);
        etlTaskCfg.setUpdateTime(date);
        taskConfigService.insertTransferTask(etlTaskCfg);
        result.setResult(etlTaskCfg);
        result.setIsSuccess(true);
        return result;
    }

    @RequestMapping(value = "/queryTransferById",method = RequestMethod.GET)
    @ResponseBody
    public EtlTaskCfg getTansferTaskByid(@RequestParam("taskid") Integer taskid) {
        EtlTaskCfg etlTaskCfg = taskConfigService.getTransferById(taskid);
        return etlTaskCfg;
    }

    @RequestMapping(value = "/updateTransfer",method = RequestMethod.POST)
    @ResponseBody
    public Result updateTransferTask(@RequestBody EtlTaskCfg etlTaskCfg) {
        Result result = new Result();
        etlTaskCfg.setUpdateTime(new Date());
        taskConfigService.updateTransfer(etlTaskCfg);
        result.setIsSuccess(true);
        return  result;
    }

    @RequestMapping(value = "/insertTransferParam",method = RequestMethod.POST)
    @ResponseBody
    public Result insertReaderAndWriter(@RequestBody EtlLoadCfg etlLoadCfg) {
        Result result = new Result();
        Date date = new Date();
        etlLoadCfg.setCreateTime(date);
        etlLoadCfg.setUpdateTime(date);
        etlLoadCfgMapper.insert(etlLoadCfg);
        result.setIsSuccess(true);
        return result;
    }

    /**
     * 预跑任务
     */
    @RequestMapping(value = "/preRunTask", method = RequestMethod.POST)
    public
    @ResponseBody
    Result preRunTask(
            @RequestParam(value = "startTime", defaultValue = "") String startTime,
            @RequestParam(value = "endTime", defaultValue = "") String endTime,
            @RequestParam(value = "taskId", defaultValue = "") String taskId) {
        logger.info("pre run task with startTime:" + startTime + ", endTime: " + endTime + ",taskid;" + taskId);
        Result result = new Result();
        Map<String,Object> map = taskService.generateInstance(startTime,endTime, Integer.parseInt(taskId));
        if (map != null) {
            result.setIsSuccess(true);
            result.setResult(map.get("num"));
            result.setResults((List<String>)map.get("instanceids"));
        } else {
            logger.error("pre run task failed!");
            result.setIsSuccess(false);
        }
        return result;
    }

    /**
     * 失效任务
     */
    @RequestMapping(value = "/invalidTask", method = RequestMethod.POST)
    public
    @ResponseBody
    Result invalidTask(@RequestParam(value = "taskId", defaultValue = "") String taskId) {
        Result result = new Result();
        try {
            Date nowTimeStamp = new Date();
            List<Integer> list = new ArrayList<Integer>();
            list.add(Integer.parseInt(taskId));
            result.setIsSuccess(taskService.invalidTask(list,nowTimeStamp));
        } catch (Exception e) {
            result.setIsSuccess(false);
            result.setMessages("失效任务失败");
            logger.error("invalid task error", e);
        }
        return result;
    }

    /**
     * 重新生效任务
     */
    @RequestMapping(value = "/validateTask", method = RequestMethod.POST)
    public
    @ResponseBody
    Result validateTask(@RequestParam(value = "taskId", defaultValue = "") String taskId) {
        Result result = new Result();
        try {
            Date nowTimeStamp = new Date();
            result.setIsSuccess(taskService.validateTask(Integer.parseInt(taskId), nowTimeStamp));
        } catch (Exception e) {
            result.setIsSuccess(false);
            result.setMessages("生效任务失败");
            logger.error("validate task error", e);
        }
        return result;
    }

    /**
     * 根据taskid查询直接孩子
     */
    @RequestMapping(value = "/relaTask", method = RequestMethod.GET)
    public
    @ResponseBody
    Result<EtlTaskCfg> getRelaImmediateChildTasks(@RequestParam("taskId") String taskId) {
        Result result = new Result();
        if (!StringUtils.hasLength(taskId)) {
            logger.error("taskid can not be null or empty");
            return null;
        }
        List<Integer> taskids = taskRelaService.getRelaChildTaskByTaskid(Integer.parseInt(taskId));
        List<EtlTaskCfg> tasks = null;
        if (taskids != null) {
            logger.info("taskid " + taskId + " has these children:" + taskids);
            tasks = taskConfigService.getTasksByIds(taskids);
        }
        if (tasks != null){
            result.setResults(tasks);
            result.setIsSuccess(true);
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
