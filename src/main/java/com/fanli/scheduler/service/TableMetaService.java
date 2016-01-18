package com.fanli.scheduler.service;

import com.fanli.scheduler.bean.TableMeta;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by wei.shen on 2015/7/23.
 */
@Service
public class TableMetaService {
    private static Logger logger = Logger.getLogger(TableMetaService.class);

    private static final String META_SERVICE_HOST = "http://localhost:3434";

    public TableMeta getTableMeta(String dbname,String tablename) {
        TableMeta meta = null;
        try {
            String url = META_SERVICE_HOST + "/" + dbname + "/" + tablename;
            //logger.info("Url of request to mds is : " + url);
            //String metaStr = Jsoup.connect(url).header("Content-Type","application/json;charset=UTF-8").get().toString();
            String metaStr = Jsoup.connect(url).ignoreContentType(true).execute().body();
            if (metaStr != null) {
                //logger.info("Get Table MetaData info ,dbname = " + dbname + ",tablename= " + tablename  +"; the returned json is " + metaStr);
            } else logger.error("Failed to get Table MetaData info ,dbname = " + dbname + ",tablename= " + tablename +" ,error");
            Gson gson = new Gson();
            meta = gson.fromJson(metaStr, TableMeta.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return meta;
    }

    public static void main(String [] args) {
        TableMetaService tableMetaService = new TableMetaService();
        tableMetaService.getTableMeta("dimvw","dim_shop_sub_shop");
    }

}

