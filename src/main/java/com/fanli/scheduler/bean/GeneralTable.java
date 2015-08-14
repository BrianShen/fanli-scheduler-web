package com.fanli.scheduler.bean;

import java.util.List;

/**
 * Created by wei.shen on 2015/8/13.
 */
public class GeneralTable {
    private String db;
    private String name;
    private List<GeneralColume> columns;
    private List<GeneralColume> partitions;
    private String dbType;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GeneralColume> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<GeneralColume> partitions) {
        this.partitions = partitions;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public List<GeneralColume> getColumns() {
        return columns;
    }

    public void setColumns(List<GeneralColume> columns) {
        this.columns = columns;
    }
}
