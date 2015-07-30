package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Log;
import com.fanli.scheduler.entity.EtlTaskStatus;
import com.fanli.scheduler.entity.EtlTaskStatusExample;
import com.fanli.scheduler.mapping.EtlTaskStatusMapper;
import com.fanli.scheduler.service.SSHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wei.shen on 2015/7/29.
 */
@Controller
public class LogController {
    @Autowired
    private SSHService sshService;
    @Autowired
    private EtlTaskStatusMapper mapper;

    @RequestMapping(value = "/log/{instanceId}",method = RequestMethod.GET)
    @ResponseBody
    public Log getLog (@PathVariable("instanceId") String instanceId) {
        EtlTaskStatusExample example = new EtlTaskStatusExample();
        example.or().andTaskStatusIdEqualTo(instanceId);

        EtlTaskStatus etlTaskStatus = mapper.selectByExample(example).get(0);
        String command = "cat " + etlTaskStatus.getLogPath();

        Log log = new Log();
        log.setLog(sshService.runSSHCommand(command));
        return log;
    }
}
