package com.fanli.scheduler.bean;

/**
 * Created by wei.shen on 2015/7/17.
 */
public class TaskQuery {
    private Integer taskId;
    private Integer taskGroupId;
    private String owner;

    @Override
    public String toString() {
        return "TaskQuery{" +
                "taskId=" + taskId +
                ", taskGroupId=" + taskGroupId +
                ", owner='" + owner + '\'' +
                '}';
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(Integer taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
