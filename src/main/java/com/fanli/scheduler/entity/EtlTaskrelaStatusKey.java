package com.fanli.scheduler.entity;

public class EtlTaskrelaStatusKey {
    private String taskStatusId;

    private String preStatusId;

    public String getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(String taskStatusId) {
        this.taskStatusId = taskStatusId == null ? null : taskStatusId.trim();
    }

    public String getPreStatusId() {
        return preStatusId;
    }

    public void setPreStatusId(String preStatusId) {
        this.preStatusId = preStatusId == null ? null : preStatusId.trim();
    }
}