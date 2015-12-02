package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.DataMonitorDo;
import com.fanli.scheduler.entity.EtlMonitorDetail;
import com.fanli.scheduler.service.DataMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wei.shen on 2015/11/25.
 */
@Controller
@RequestMapping("/dataMonitor")
public class DataPlatformMonitorController {
    @Autowired
    private DataMonitorService dataMonitorService;


    @RequestMapping(value = "/{taskId}",method = RequestMethod.GET)
    @ResponseBody
    public DataMonitorDo getMonitorConfig(@PathVariable("taskId") Long taskId) {
        DataMonitorDo dataMonitorDo = new DataMonitorDo();
        return dataMonitorService.getDataMonitorConfig(taskId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMonitorConfig(@RequestBody DataMonitorDo dataMonitorDo) {
        dataMonitorService.SaveMonitorConfigs(dataMonitorDo);
    }

    @RequestMapping(value = "/{taskId}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateMonitorConfig(@RequestBody DataMonitorDo dataMonitorDo) {
        dataMonitorService.updateMonitor(dataMonitorDo);
    }
}
