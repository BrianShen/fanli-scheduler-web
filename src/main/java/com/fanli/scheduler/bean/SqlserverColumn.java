package com.fanli.scheduler.bean;

import javax.swing.text.StyledEditorKit;

/**
 * Created by wei.shen on 2015/8/13.
 */
public class SqlserverColumn extends GeneralColume{
    private Boolean isPrimary;
    private int columeSize;

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public int getColumeSize() {
        return columeSize;
    }

    public void setColumeSize(int columeSize) {
        this.columeSize = columeSize;
    }
}
