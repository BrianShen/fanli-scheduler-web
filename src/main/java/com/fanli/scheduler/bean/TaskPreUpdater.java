package com.fanli.scheduler.bean;

import com.fanli.scheduler.entity.EtlTaskrelaCfg;

import java.util.List;

/**
 * Created by wei.shen on 2015/10/12.
 */
public class TaskPreUpdater {
    private Integer taskId;
    private List<EtlTaskrelaCfg> cfgs;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public List<EtlTaskrelaCfg> getCfgs() {
        return cfgs;
    }

    public void setCfgs(List<EtlTaskrelaCfg> cfgs) {
        this.cfgs = cfgs;
    }
}
