package com.fanli.scheduler.bean;

/**
 * Created by wei.shen on 2015/9/28.
 */
public class TaskRelaDo {
    private Integer taskId;
    private String offset;
    private String taskName;
    private String cycle;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getOffset() {

        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
