package com.fanli.scheduler.service;

import com.fanli.scheduler.constants.Const;
import com.fanli.scheduler.entity.EtlTaskStatus;
import com.fanli.scheduler.utils.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wei.shen on 2015/8/31.
 */

@Service
public class InstanceService {
    private static Logger logger = Logger.getLogger(InstanceService.class);

    public EtlTaskStatus generateInstances(Date initDate,Date triggerDate,EtlTaskCfg etlTaskCfg) {
        EtlTaskStatus instance = new EtlTaskStatus();
        String taskStatusId = "pre_" + CommonUtils.generateInstanceId(String.valueOf(etlTaskCfg.getTaskId()),
                String.valueOf(etlTaskCfg.getCycle()),initDate) + "_" + CommonUtils.Date2String(triggerDate);
        instance.setTaskStatusId(taskStatusId);
        instance.setTaskId(etlTaskCfg.getTaskId());
        instance.setTaskName(etlTaskCfg.getTaskName());
        instance.setTaskGroupId(etlTaskCfg.getTaskGroupId());
        instance.setResource(etlTaskCfg.getResource());
        String command = StringUtils.isEmpty(etlTaskCfg.getCommand())?null:CommonUtils.CaldtReplace(etlTaskCfg.getCommand(),
                etlTaskCfg.getOffset(),initDate);
        instance.setCommand(command);
        String logPath = Const.TASK_LOG_DIR + File.separator + taskStatusId + "." + CommonUtils.getDay8() + ".log";
        instance.setLogPath(logPath);
        instance.setCycle(etlTaskCfg.getCycle());
        instance.setTimeId(CommonUtils.getDay10(initDate));
        instance.setStatus(0);
        instance.setIfWait(etlTaskCfg.getIfWait());
        instance.setIfRecall(0);
        instance.setIfPre(0);
        instance.setPriority(etlTaskCfg.getPriority());
        instance.setRecallNum(0);
        instance.setRunNum(0);
        instance.setRecallInterval(etlTaskCfg.getRecallInterval());
        instance.setRecallLimit(etlTaskCfg.getRecallLimit());
        instance.setTimeStamp(new Date());
        instance.setType(etlTaskCfg.getType());
        String baseCaldt = CommonUtils.getLastDay10(initDate);
        String caldt = null;
        try {
            caldt = CommonUtils.getCalDt(baseCaldt, etlTaskCfg.getOffset());
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            caldt = null;
        }
        instance.setCalDt(caldt);
        instance.setFreq(etlTaskCfg.getFreq());
        instance.setOwner(etlTaskCfg.getOwner());
        Calendar c = Calendar.getInstance();
        c.setTime(triggerDate);
        instance.setTriggerTime(c.getTimeInMillis());
        instance.setWaitCode(etlTaskCfg.getWaitCode());
        instance.setRecallCode(etlTaskCfg.getRecallCode());
        instance.setSuccessCode(etlTaskCfg.getSuccessCode());
        instance.setJobCode(-1);
        instance.setRunningPrio(-1);
        instance.setTimeout(etlTaskCfg.getTimeout());
        instance.setConcurrency(1);
        return instance;
    }
}
