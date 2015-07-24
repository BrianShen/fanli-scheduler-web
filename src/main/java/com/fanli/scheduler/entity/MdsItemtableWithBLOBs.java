package com.fanli.scheduler.entity;

public class MdsItemtableWithBLOBs extends MdsItemtable {
    private String createTableInfo;

    private String columnDescription;

    public String getCreateTableInfo() {
        return createTableInfo;
    }

    public void setCreateTableInfo(String createTableInfo) {
        this.createTableInfo = createTableInfo == null ? null : createTableInfo.trim();
    }

    public String getColumnDescription() {
        return columnDescription;
    }

    public void setColumnDescription(String columnDescription) {
        this.columnDescription = columnDescription == null ? null : columnDescription.trim();
    }
}