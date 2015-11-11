package com.fanli.scheduler.service;

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
    Boolean validateTask(Integer taskid,Date updateTime);
    Boolean deleteTask(Integer taskid,Date updateTime);
}
