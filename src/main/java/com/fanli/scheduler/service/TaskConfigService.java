package com.fanli.scheduler.service;

import com.fanli.scheduler.bean.TaskQuery;
import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.entity.EtlTaskrelaCfg;

import java.util.List;

/**
 * Created by wei.shen on 2015/7/15.
 */
public interface TaskConfigService {
    int insertTaskConfig(EtlTaskCfg etlTaskCfg);
    EtlTaskCfg getTaskById(Integer id);
    List<EtlTaskCfg> getTaskByParams(TaskQuery taskQuery);
    int insertTaskRela(EtlTaskrelaCfg etlTaskrelaCfg);
    int updateTask(EtlTaskCfg etlTaskCfg);
    int deleteTaskRela(Integer taskid);
    int insertTransferTask(EtlTaskCfg etlTaskCfg);
    EtlTaskCfg getTransferById(Integer taskid);
    int updateTransfer(EtlTaskCfg etlTaskCfg);
    List<EtlTaskCfg> getTasksByIds(List<Integer> ids);
}
