package com.fanli.scheduler.bean;

/**
 * Created by wei.shen on 2015/11/3.
 */
public class JobStatus {
    private Integer taskId;
    private String instanceId;
    private Integer status;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
