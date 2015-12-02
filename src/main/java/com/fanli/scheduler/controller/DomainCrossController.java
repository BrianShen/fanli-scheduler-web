package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.MetaColumn;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.bean.TableMeta;
import com.fanli.scheduler.service.SSHService;
import com.fanli.scheduler.service.TableMetaService;
import com.fanli.scheduler.utils.PropertiesUtil;
import com.jcraft.jsch.JSchException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wei.shen on 2015/7/23.
 */

@RequestMapping("/domain")
@Controller
public class DomainCrossController {
    private static Logger logger = Logger.getLogger(DomainCrossController.class);
    @Autowired
    private TableMetaService tableMetaService;
    @Autowired
    private SSHService sshService;


    @RequestMapping(value = "/meta")
    @ResponseBody
    public TableMeta getMetaInfo(@RequestParam("db") String db,@RequestParam("table") String table) {
        if ("".equals(db)) logger.error("warning,db is an empty String");
        if ("".equals(table)) logger.error("warning,table is an empty String");
        return tableMetaService.getTableMeta(db,table);
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
       // return tableMeta;
    }

    @RequestMapping(value = "/ddlSql",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getDDlSql(@RequestParam String path) {
        Map<String,Object> map = null;
        Properties p = PropertiesUtil.getProperties("/common.properties");
        String dolBaseDir = p.getProperty("DolBaseDir");
        if (path.charAt(0) != '/') path = "/" + path;
        try {
            map = sshService.runSSHCommandWithLogAndResult("cat " + dolBaseDir + path);
        } catch (JSchException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        };
        return map;
    }

}
