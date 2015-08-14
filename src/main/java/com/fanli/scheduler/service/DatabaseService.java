package com.fanli.scheduler.service;

import com.fanli.scheduler.bean.GeneralColume;
import com.fanli.scheduler.bean.GeneralTable;
import com.fanli.scheduler.utils.ConnectMan;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wei.shen on 2015/8/11.
 */
@Service
public class DatabaseService {
    public GeneralTable getDateTimeIncreaseField(String conn,String db,String table) {
        GeneralTable gt = getTableDetail(conn,db,table);
        List<GeneralColume> list = gt.getColumns();
        for (int i = 0;i < list.size();i ++) {
            if (!"datetime".equalsIgnoreCase(list.get(i).getType()) && !"timestamp".equalsIgnoreCase(list.get(i).getType())) {
                list.remove(i);
                i --;
            }
        }
        return gt;
    }
    public GeneralTable getTableDetail(String conn,String db,String table) {
        return ConnectMan.INSTANCE.getTableDetails(conn,db,table);
    }

    public List<String> getTablesByConnectionProp(String prop) {
        List<String> list = null;
        try {
            list = ConnectMan.INSTANCE.getAllDatabases(prop);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean checkTableExists(String conn,String db,String table) {
        return ConnectMan.INSTANCE.isTableExists(conn,db,table);
    }

    public List<String> getTableInfo(String conn, String db, String table) {
        return ConnectMan.INSTANCE.getTablesInfo(conn,db,table);
    }
}
