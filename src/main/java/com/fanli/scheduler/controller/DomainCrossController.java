package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.TableMeta;
import com.fanli.scheduler.service.TableMetaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wei.shen on 2015/7/23.
 */

@RequestMapping("/domain")
@Controller
public class DomainCrossController {
    private static Logger logger = Logger.getLogger(DomainCrossController.class);
    @Autowired
    private TableMetaService tableMetaService;
    @RequestMapping(value = "/meta")
    @ResponseBody
    public TableMeta getMetaInfo(@RequestParam("db") String db,@RequestParam("table") String table) {
        if ("".equals(db)) logger.error("warning,db is an empty String");
        if ("".equals(table)) logger.error("warning,table is an empty String");
        return tableMetaService.getTableMeta(db,table);
    }

}
