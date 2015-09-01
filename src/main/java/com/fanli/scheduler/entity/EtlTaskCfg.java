package com.fanli.scheduler.entity;

import java.util.Date;

public class EtlTaskCfg {
    private Integer taskId;

    private Integer taskGroupId;

    private String taskName;

    private String resource;

    private String command;

    private String cycle;

    private Integer priority;

    private Integer ifRecall;

    private Integer ifWait;

    private Integer ifPre;

    private Integer ifEnable;

    private String freq;

    private String owner;

    private String waitCode;

    private String recallCode;

    private String successCode;

    private Integer timeout;

    private Integer recallInterval;

    private String logFile;

    private String addUser;

    private Date addTime;

    private String updateUser;

    private Date updateTime;

    private Integer type;

    private String offset;

    private Integer recallLimit;

    private Integer concurrency;

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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource == null ? null : resource.trim();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command == null ? null : command.trim();
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIfRecall() {
        return ifRecall;
    }

    public void setIfRecall(Integer ifRecall) {
        this.ifRecall = ifRecall;
    }

    public Integer getIfWait() {
        return ifWait;
    }

    public void setIfWait(Integer ifWait) {
        this.ifWait = ifWait;
    }

    public Integer getIfPre() {
        return ifPre;
    }

    public void setIfPre(Integer ifPre) {
        this.ifPre = ifPre;
    }

    public Integer getIfEnable() {
        return ifEnable;
    }

    public void setIfEnable(Integer ifEnable) {
        this.ifEnable = ifEnable;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq == null ? null : freq.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getWaitCode() {
        return waitCode;
    }

    public void setWaitCode(String waitCode) {
        this.waitCode = waitCode == null ? null : waitCode.trim();
    }

    public String getRecallCode() {
        return recallCode;
    }

    public void setRecallCode(String recallCode) {
        this.recallCode = recallCode == null ? null : recallCode.trim();
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode == null ? null : successCode.trim();
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRecallInterval() {
        return recallInterval;
    }

    public void setRecallInterval(Integer recallInterval) {
        this.recallInterval = recallInterval;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile == null ? null : logFile.trim();
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser == null ? null : addUser.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset == null ? null : offset.trim();
    }

    public Integer getRecallLimit() {
        return recallLimit;
    }

    public void setRecallLimit(Integer recallLimit) {
        this.recallLimit = recallLimit;
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Integer concurrency) {
        this.concurrency = concurrency;
    }

    @Override
    public String toString() {
        return "EtlTaskCfg{" +
                "taskId=" + taskId +
                ", taskGroupId=" + taskGroupId +
                ", taskName='" + taskName + '\'' +
                ", resource='" + resource + '\'' +
                ", command='" + command + '\'' +
                ", cycle='" + cycle + '\'' +
                ", priority=" + priority +
                ", ifRecall=" + ifRecall +
                ", ifWait=" + ifWait +
                ", ifPre=" + ifPre +
                ", ifEnable=" + ifEnable +
                ", freq='" + freq + '\'' +
                ", owner='" + owner + '\'' +
                ", waitCode='" + waitCode + '\'' +
                ", recallCode='" + recallCode + '\'' +
                ", successCode='" + successCode + '\'' +
                ", timeout=" + timeout +
                ", recallInterval=" + recallInterval +
                ", logFile='" + logFile + '\'' +
                ", addUser='" + addUser + '\'' +
                ", addTime=" + addTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", offset='" + offset + '\'' +
                ", recallLimit=" + recallLimit +
                ", concurrency=" + concurrency +
                '}';
    }
}