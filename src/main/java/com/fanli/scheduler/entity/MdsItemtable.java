package com.fanli.scheduler.entity;

public class MdsItemtable {
    private Integer tableId;

    private String tableName;

    private String description;

    private String bloodRelation;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBloodRelation() {
        return bloodRelation;
    }

    public void setBloodRelation(String bloodRelation) {
        this.bloodRelation = bloodRelation == null ? null : bloodRelation.trim();
    }
}