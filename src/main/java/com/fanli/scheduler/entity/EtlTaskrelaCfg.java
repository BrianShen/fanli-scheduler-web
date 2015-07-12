package com.fanli.scheduler.entity;

import java.util.Date;

public class EtlTaskrelaCfg extends EtlTaskrelaCfgKey {
    private Integer ifEnable;

    private Date updatetime;

    private Date timeStamp;

    public Integer getIfEnable() {
        return ifEnable;
    }

    public void setIfEnable(Integer ifEnable) {
        this.ifEnable = ifEnable;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}