package com.fanli.scheduler.bean;

/**
 * Created by wei.shen on 2015/7/17.
 */
public class TaskQuery {
    private String taskId;
    private Integer taskGroupId;
    private String owner;
    private Integer isValid;

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "TaskQuery{" +
                "taskId='" + taskId + '\'' +
                ", taskGroupId=" + taskGroupId +
                ", owner='" + owner + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
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
