package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.service.TaskService;

import java.util.List;

/**
 * Created by wei.shen on 2015/8/21.
 */
public class TaskServiceImpl  implements TaskService {
    @Override
    public List<EtlTaskCfg> getChildrenByTaskid(Integer taskid) {
        return null;
    }
}
