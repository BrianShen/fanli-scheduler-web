package com.fanli.scheduler.bean;

import java.util.List;

/**
 * Created by wei.shen on 2015/8/13.
 */
public class GeneralTable {
    private String name;
    private List<GeneralColumn> columns;
    private List<GeneralColumn> partitions;
    private String dbType;
    private String schema;
    private String db;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GeneralColumn> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<GeneralColumn> partitions) {
        this.partitions = partitions;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public List<GeneralColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<GeneralColumn> columns) {
        this.columns = columns;
    }
}