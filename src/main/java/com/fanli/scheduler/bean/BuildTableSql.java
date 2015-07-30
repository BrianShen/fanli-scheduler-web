package com.fanli.scheduler.bean;

/**
 * Created by wei.shen on 2015/7/30.
 */
public class BuildTableSql {
    private String sql;

    public String getSql() {
        return sql;
    }

    @Override
    public String toString() {
        return "BuildTableSql{" +
                "sql='" + sql + '\'' +
                '}';
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
