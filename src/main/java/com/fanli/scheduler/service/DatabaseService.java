package com.fanli.scheduler.service;

import com.fanli.scheduler.utils.ConnectMan;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wei.shen on 2015/8/11.
 */
@Service
public class DatabaseService {
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
}
