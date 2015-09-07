package com.fanli.scheduler.service;

import java.util.List;

/**
 * Created by wei.shen on 2015/8/31.
 */
public interface TaskRelaService {
    List<Integer> getRelaChildTaskByTaskid(Integer taskid);
}
