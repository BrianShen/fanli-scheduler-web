package com.fanli.scheduler.bean;

import java.util.List;

/**
 * Created by wei.shen on 2015/7/23.
 */
public class TableMeta {
    private String database;
    private String table;
    private List<MetaColumn> columns;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<MetaColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MetaColumn> columns) {
        this.columns = columns;
    }
}
