package com.fanli.scheduler.service;

import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.entity.EtlTaskStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wei.shen on 2015/8/21.
 */
public interface TaskService {
    List<Integer> getChildrenByTaskid(Integer taskid);
    Map<String,Object> generateInstance(String start,String end,Integer taskid);
    Boolean invalidTask(List<Integer> taskids,Date updateTime);
}
