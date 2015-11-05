package com.fanli.scheduler.bean;

import com.fanli.scheduler.entity.EtlTaskStatus;

import java.util.List;

/**
 * Created by wei.shen on 2015/11/3.
 */
public class InstanceRelaNode {
    private JobStatus self;
    private List<InstanceRelaNode> parent;

    public List<InstanceRelaNode> getParent() {
        return parent;
    }

    public void setParent(List<InstanceRelaNode> parent) {
        this.parent = parent;
    }

    public JobStatus getSelf() {
        return self;
    }

    public void setSelf(JobStatus self) {
        this.self = self;
    }
}
