package com.fanli.scheduler.service;

import com.fanli.scheduler.bean.GeneralTable;
import com.fanli.scheduler.bean.MetaColumn;
import com.fanli.scheduler.bean.TableMeta;
import com.fanli.scheduler.utils.TableCreator;
import org.apache.ibatis.metadata.Column;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei.shen on 2015/7/27.
 */
@Service
public class TableService {

    public String getSql(GeneralTable generalTable) {
        return TableCreator.generateCreateTableSentence(generalTable);
    }

    public Boolean buildTable(String sql) {
        ProcessBuilder processBuilder = new ProcessBuilder("sh",sql);
        return null;
    }

    private StringBuffer generateBuildTable(String db,String table,List<MetaColumn> columns,List<MetaColumn> partitions) {
        StringBuffer sb = new StringBuffer();
        sb.append("use " + db + ";\n");
        sb.append("drop table if exists " + table + ";\n");
        sb.append("create table " + table + "\n(\n");
        for(int i = 0;i < columns.size() - 1;i ++) {
            sb.append(columns.get(i).getName() + " " + columns.get(i).getType() + ",\n");
        }
        sb.append(columns.get(columns.size() - 1).getName() + " " + columns.get(columns.size() - 1).getType() + "\n");
        sb.append(")\n");
        if (partitions != null) {
            sb.append("PARTITIONED BY(");
            for (int i = 0;i < partitions.size() - 1;i ++) {
                sb.append(partitions.get(i).getName() + " " + partitions.get(i).getType() + ",");
            }
            sb.append(partitions.get(partitions.size() - 1).getName() + " " + partitions.get(partitions.size() - 1).getType() + ")\n");
        }
        sb.append("STORED AS ORC;");
        return sb;
    }

//    public static void main(String[] args) {
//        TableService tableService = new TableService();
//        TableMeta tableMeta = new TableMeta();
//        tableMeta.setTable("dim_shop_sub_shop");
//        tableMeta.setDatabase("dimvw");
//        List<MetaColumn> list = new ArrayList<MetaColumn>();
//        MetaColumn metaColumn1 = new MetaColumn();
//        metaColumn1.setName("created_at");
//        metaColumn1.setType("string");
//        metaColumn1.setComment("创建时间");
//        MetaColumn metaColumn2 = new MetaColumn();
//        metaColumn2.setName("created_at");
//        metaColumn2.setType("string");
//        metaColumn2.setComment("创建时间");
//        MetaColumn metaColumn3 = new MetaColumn();
//        metaColumn3.setName("created_at");
//        metaColumn3.setType("string");
//        metaColumn3.setComment("创建时间");
//        MetaColumn metaColumn4 = new MetaColumn();
//        metaColumn4.setName("created_at");
//        metaColumn4.setType("string");
//        metaColumn4.setComment("创建时间");
//        list.add(metaColumn1);
//        list.add(metaColumn2);
//        list.add(metaColumn3);
//        list.add(metaColumn4);
//        tableMeta.setColumns(list);
//        MetaColumn metaColumn5 = new MetaColumn();
//        metaColumn5.setName("created_at");
//        metaColumn5.setType("string");
//        metaColumn5.setComment("创建时间");
//        List<MetaColumn> list2 = new ArrayList<MetaColumn>();
//        list2.add(metaColumn5);
//        tableMeta.setPartitions(list2);
//        String sql = tableService.getSql(tableMeta);
//        System.out.println(sql);
//    }

}
