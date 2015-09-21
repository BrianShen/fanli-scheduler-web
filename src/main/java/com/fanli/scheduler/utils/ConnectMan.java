package com.fanli.scheduler.utils;

import com.fanli.scheduler.bean.GeneralColumn;
import com.fanli.scheduler.bean.GeneralTable;
import com.fanli.scheduler.bean.JdbcObject;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by zcfrank1st on 8/11/15.
 */
public enum ConnectMan {
    INSTANCE;

    private static Logger logger = Logger.getLogger(ConnectMan.class);

    private static Properties config;
    static {
        InputStream in = ConnectMan.class.getResourceAsStream("/connection.properties");
        config = new Properties();
        try {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JdbcObject getJdbcObject(String connectProperty) {
        String ip = config.getProperty(connectProperty +".ip");
        String port = config.getProperty(connectProperty + ".port");
        String username = config.getProperty(connectProperty + ".username");
        String password = config.getProperty(connectProperty + ".password");

        JdbcObject jdbcObject = new JdbcObject();
        jdbcObject.setIp(ip);
        jdbcObject.setPort(port);
        jdbcObject.setUsername(username);
        jdbcObject.setPassword(password);

        return jdbcObject;
    }

    private String getJdbcUri (String connectProperty, JdbcObject jdbcObject) throws ClassNotFoundException {
        if (connectProperty.contains("mysql")) {
            Class.forName("com.mysql.jdbc.Driver");
            return "jdbc:mysql://" + jdbcObject.getIp() + ":" + jdbcObject.getPort()
                    + "?useUnicode=true&characterEncoding=utf8";
        } else if (connectProperty.contains("sqlserver")) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return "jdbc:sqlserver://" + jdbcObject.getIp() + ":" + jdbcObject.getPort();
        } else {
            throw new RuntimeException("not support jdbc uri, current support mysql and sqlserver");
        }
    }

    public Connection getConnetion(String connectProperty) throws Exception {
        JdbcObject jdbcObject = getJdbcObject(connectProperty);
        String jdbcUri = getJdbcUri(connectProperty, jdbcObject);

        return DriverManager.getConnection(jdbcUri, jdbcObject.getUsername(), jdbcObject.getPassword());
    }

    public static void main(String[] args) {
        String connectProperty = "sqlserver80";
        String ip = config.getProperty(connectProperty +".ip");
        String port = config.getProperty(connectProperty + ".port");
        String username = config.getProperty(connectProperty + ".username");
        String password = config.getProperty(connectProperty + ".password");

        JdbcObject jdbcObject = new JdbcObject();
        jdbcObject.setIp(ip);
        jdbcObject.setPort(port);
        jdbcObject.setUsername(username);
        jdbcObject.setPassword(password);
        System.out.println(jdbcObject);

    }

    public List<String> getAllDatabases (String connectProperty) throws Exception {
        Connection con = getConnetion(connectProperty);
        DatabaseMetaData meta = con.getMetaData();
        ResultSet rs = meta.getCatalogs();

        List<String> databases = new ArrayList<String>();
        while (rs.next()) {
            databases.add(rs.getString("TABLE_CAT"));
        }
        rs.close();
        con.close();
        return databases;
    }

    public Boolean isTableExists(String connProp,String db,String table)  {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = getConnetion(connProp);
            DatabaseMetaData meta = conn.getMetaData();
            rs = meta.getTables(db, null, table, null);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            assert rs != null;
            try {
                rs.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public List<String> getTablesInfo(String conn,String db,String table) {
        Connection connection = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        try {
            connection = getConnetion(conn);
            rs = connection.getMetaData().getTables(db, null, table, null);
            while (rs.next()) {
                list.add(rs.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            assert rs != null;
            try {
                if (rs != null) {
                    rs.close();
                }
                if (connection!=null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public GeneralTable getTableDetails(String conn,String db,String table) {
        GeneralTable gt = new GeneralTable();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = getConnetion(conn);
            rs = connection.getMetaData().getColumns(db, null, table, null);
            List<GeneralColumn> list = new ArrayList<GeneralColumn>();
            while (rs.next()) {
                GeneralColumn gc = new GeneralColumn();
                gc.setName(rs.getString("COLUMN_NAME"));
                gc.setType(rs.getString("TYPE_NAME"));
                gc.setIndex(rs.getString("ORDINAL_POSITION"));
                gc.setComment(rs.getString("REMARKS"));
                list.add(gc);
            }
            gt.setColumns(list);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            assert rs != null;
            try {
                rs.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        gt.setName(table);
        return gt;

    }

    public int createTable(String conn,String db,String sql) {
        int ret = -1;
        Connection connection = null;
        Statement stmt = null;
        try {
            JdbcObject jdbcObject = getJdbcObject(conn);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + jdbcObject.getIp() + ":" + jdbcObject.getPort() + ";" + "DatabaseName=" + db;
            logger.info("jdbc sqlserver url is: " + url);
            connection = DriverManager.getConnection(url,jdbcObject.getUsername(),jdbcObject.getPassword());
            logger.info("The jdbc connection instance is: " + connection);
            stmt = connection.createStatement();
            ret = stmt.executeUpdate(sql);
            logger.info("execute create table return code is :" + ret);
        }catch (Exception e) {
            logger.error("create table failed,the error is :" + e.getMessage());
        }finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se){
                se.printStackTrace();
            }// do nothing
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
        return ret;
    }


}
