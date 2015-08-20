package com.fanli.scheduler.bean;

/**
 * Created by wei.shen on 2015/8/17.
 */
public class JdbcBuildTable {
    private String connectProp;
    private String db;
    private String sql;

    public String getConnectProp() {
        return connectProp;
    }

    public void setConnectProp(String connectProp) {
        this.connectProp = connectProp;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
