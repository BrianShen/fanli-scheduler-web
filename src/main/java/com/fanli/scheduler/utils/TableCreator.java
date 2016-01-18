package com.fanli.scheduler.utils;

import com.fanli.scheduler.bean.GeneralColumn;
import com.fanli.scheduler.bean.GeneralTable;

import javax.sql.rowset.spi.TransactionalWriter;
import java.util.List;

/**
 * Created by wei.shen on 8/13/15.
 */
public class TableCreator {
    private static final String TYPE_HIVE = "hive";
    private static final String TYPE_SQLSERVER = "sqlserver";

    public static String generateCreateTableSentence(GeneralTable generalTable) {
        return genFromType(generalTable);
    }

    private static String genFromType(GeneralTable generalTable) {
        if (generalTable.getDbType().equals(TYPE_HIVE)) {
            return genHiveCreateTableSentence(generalTable);
        } else if (generalTable.getDbType().equals(TYPE_SQLSERVER)) {
            return genSqlserverCreateTableSentence(generalTable);
        } else {
            throw new RuntimeException("generate table, type not supported!");
        }
    }

    private static String genHiveCreateTableSentence(GeneralTable generalTable) {
        StringBuilder builder = new StringBuilder();
        builder = buildHead(builder,generalTable.getDb(),generalTable.getName());
        for (GeneralColumn column : generalTable.getColumns()) {
            String columnName = column.getName();
            String columnType = TypeTransformer.transTohive(column.getType());
            String columnComment = column.getComment();
            builder = buildBody(builder, columnName, columnType, columnComment);
        }
        List<GeneralColumn> partitions = generalTable.getPartitions();
        if (partitions.size() > 0) {
            builder = buildPartition(deleteRedundancy(builder), partitions);
        }
        builder = buildTail(deleteRedundancy(builder), TYPE_HIVE);
        return builder.toString();
    }

    private static String genSqlserverCreateTableSentence(GeneralTable generalTable) {
        StringBuilder builder = new StringBuilder();
        builder = buildSqlserverHeader(builder, generalTable.getName(),generalTable.getSchema());
        for (GeneralColumn column : generalTable.getColumns()) {
            String columnName = column.getName();
            String columnType = TypeTransformer.transformHive(column.getType());
            String columnComment = column.getComment();
            builder = buildSqlserverBody(builder, columnName, columnType, columnComment);
        }

        builder = buildTail(deleteRedundancy(builder), TYPE_SQLSERVER);
        builder = buildSqlserverComment(builder, generalTable);
        return builder.toString();
    }

    private static StringBuilder buildSqlserverHeader(StringBuilder builder,String name,String schema) {
        return builder.append("CREATE TABLE [").append(schema).append("].[").append(name).append("] (\n");
    }

    private static StringBuilder buildHead (StringBuilder builder, String db,String name) {
        return builder.append("CREATE EXTERNAL TABLE ").append(db).append(".").append(name).append(" ( \n");
    }

    private static StringBuilder buildSqlserverBody (StringBuilder builder, String name, String type, String comment) {
        return builder.append("    ").append(name).append(" ")
                .append(type).append(",\n");
    }

    private static StringBuilder buildSqlserverComment(StringBuilder builder,GeneralTable generalTable) {
        String table = generalTable.getName();
        String schema = generalTable.getSchema();
        for (GeneralColumn column:generalTable.getColumns()) {
            builder.append("\n")
                    .append("EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'")
                    .append(column.getComment())
                    .append("' , @level0type=N'SCHEMA',@level0name=N'")
                    .append(schema)
                    .append("', @level1type=N'TABLE',@level1name=N'")
                    .append(table)
                    .append("', @level2type=N'COLUMN',@level2name=N'")
                    .append(column.getName())
                    .append("'");
        }
        return builder;
    }



    private static StringBuilder buildBody (StringBuilder builder, String name, String type, String comment) {
        if (comment != null) {
            comment = comment.replace(";"," ").replace("："," ");
        }
        return builder.append("    ").append(name).append(" ")
                .append(type).append(" ")
                .append("COMMENT '").append(comment==null?"":comment).append("'").append(",\n");
    }

    private static StringBuilder buildPartition(StringBuilder builder, List<GeneralColumn> partitions) {
        builder.append("\n").append(")");
        builder.append(" PARTITIONED BY (").append("\n");
        for (GeneralColumn generalColumn : partitions) {
            builder.append("    ").append(generalColumn.getName()).append(" ")
                    .append(generalColumn.getType()).append(" ")
                    .append("COMMENT '").append(generalColumn.getComment()==null?"":generalColumn.getComment()).append("'").append(",\n");
        }
        return builder;
    }

    private static StringBuilder buildTail (StringBuilder builder, String type) {
        builder.append("\n").append(")");
        if (type.equals(TYPE_HIVE)) {
            builder.append("\n").append("ROW FORMAT DELIMITED").append("\n")
                    .append("FIELDS TERMINATED BY '\\t'");
        }
        return builder;
    }

    private static StringBuilder deleteRedundancy(StringBuilder builder) {
        return builder.delete(builder.length() - 2, builder.length());
    }
}
