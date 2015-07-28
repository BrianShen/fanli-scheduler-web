package com.fanli.scheduler.bean;

/**
 * Created by wei.shen on 2015/7/28.
 */
public class TaskAdder {
    private String preId;
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "TaskAdder{" +
                "preId='" + preId + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }

    public String getPreId() {
        return preId;
    }

    public void setPreId(String preId) {
        this.preId = preId;
    }
}
