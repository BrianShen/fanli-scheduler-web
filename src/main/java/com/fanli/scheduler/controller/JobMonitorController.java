package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.EtlTaskStatus;
import com.fanli.scheduler.service.JobMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wei.shen on 2015/7/29.
 */
@Controller
@RequestMapping("/monitor")
public class JobMonitorController {
    @Autowired
    private JobMonitorService jobMonitorService;

    @ResponseBody
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Result<EtlTaskStatus> queryTaskStatus(
            @RequestParam(value = "taskId",defaultValue = "") String taskid,@RequestParam(value = "owner",defaultValue = "")String owner,
            @RequestParam(value = "status",defaultValue = "")String status,@RequestParam(value = "startTime",defaultValue = "")String startTime,
            @RequestParam(value = "endTime",defaultValue = "") String endTime) {
        Result<EtlTaskStatus> result = new Result<EtlTaskStatus>();

        List<EtlTaskStatus> list = jobMonitorService.queryTaskStatuses(startTime,endTime,"".equals(taskid)?null:Integer.parseInt(taskid),owner,"".equals(status)?null:Integer.parseInt(status));
        result.setResults(list);

        result.setIsSuccess(true);

        System.out.println(owner);
        return result;

    }
}
