package com.fanli.scheduler.service;

import com.fanli.scheduler.entity.EtlTaskCfg;

import java.util.List;

/**
 * Created by wei.shen on 2015/8/21.
 */
public interface TaskService {
    List<EtlTaskCfg> getChildrenByTaskid(Integer taskid);
}
