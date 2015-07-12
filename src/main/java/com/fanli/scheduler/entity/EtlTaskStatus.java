package com.fanli.scheduler.entity;

import java.util.Date;

public class EtlTaskStatus {
    private String taskStatusId;

    private Integer taskId;

    private String taskName;

    private Integer taskGroupId;

    private String resource;

    private String command;

    private String logPath;

    private String cycle;

    private String timeId;

    private Integer status;

    private Integer ifWait;

    private Integer ifRecall;

    private Integer ifPre;

    private Integer priority;

    private Integer recallNum;

    private Integer runNum;

    private Integer recallInterval;

    private Integer recallLimit;

    private Date startTime;

    private Date endTime;

    private Date timeStamp;

    private Integer type;

    private String tableName;

    private String calDt;

    private String freq;

    private String owner;

    private Long triggerTime;

    private String waitCode;

    private String recallCode;

    private String successCode;

    private Integer jobCode;

    private Integer runningPrio;

    private Integer timeout;

    private String jobCodemsg;

    private String taskCommitter;

    private Integer concurrency;

    public String getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(String taskStatusId) {
        this.taskStatusId = taskStatusId == null ? null : taskStatusId.trim();
    }

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
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Integer getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(Integer taskGroupId) {
        this.taskGroupId = taskGroupId;
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

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath == null ? null : logPath.trim();
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId == null ? null : timeId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIfWait() {
        return ifWait;
    }

    public void setIfWait(Integer ifWait) {
        this.ifWait = ifWait;
    }

    public Integer getIfRecall() {
        return ifRecall;
    }

    public void setIfRecall(Integer ifRecall) {
        this.ifRecall = ifRecall;
    }

    public Integer getIfPre() {
        return ifPre;
    }

    public void setIfPre(Integer ifPre) {
        this.ifPre = ifPre;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getRecallNum() {
        return recallNum;
    }

    public void setRecallNum(Integer recallNum) {
        this.recallNum = recallNum;
    }

    public Integer getRunNum() {
        return runNum;
    }

    public void setRunNum(Integer runNum) {
        this.runNum = runNum;
    }

    public Integer getRecallInterval() {
        return recallInterval;
    }

    public void setRecallInterval(Integer recallInterval) {
        this.recallInterval = recallInterval;
    }

    public Integer getRecallLimit() {
        return recallLimit;
    }

    public void setRecallLimit(Integer recallLimit) {
        this.recallLimit = recallLimit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getCalDt() {
        return calDt;
    }

    public void setCalDt(String calDt) {
        this.calDt = calDt == null ? null : calDt.trim();
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

    public Long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;
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

    public Integer getJobCode() {
        return jobCode;
    }

    public void setJobCode(Integer jobCode) {
        this.jobCode = jobCode;
    }

    public Integer getRunningPrio() {
        return runningPrio;
    }

    public void setRunningPrio(Integer runningPrio) {
        this.runningPrio = runningPrio;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getJobCodemsg() {
        return jobCodemsg;
    }

    public void setJobCodemsg(String jobCodemsg) {
        this.jobCodemsg = jobCodemsg == null ? null : jobCodemsg.trim();
    }

    public String getTaskCommitter() {
        return taskCommitter;
    }

    public void setTaskCommitter(String taskCommitter) {
        this.taskCommitter = taskCommitter == null ? null : taskCommitter.trim();
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Integer concurrency) {
        this.concurrency = concurrency;
    }
}