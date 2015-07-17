package com.fanli.scheduler.service;

import com.fanli.scheduler.entity.EtlTaskCfg;

/**
 * Created by wei.shen on 2015/7/15.
 */
public interface TaskConfigService {
    int insertTaskConfig(EtlTaskCfg etlTaskCfg);
    EtlTaskCfg getTaskById(Integer id);
}
