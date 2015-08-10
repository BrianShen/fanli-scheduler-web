package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.service.DolService;
import com.fanli.scheduler.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Properties;

/**
 * Created by wei.shen on 2015/7/15.
 */
@Controller
@RequestMapping("/dol")
public class DolController {

    @Autowired
    private DolService dolService;
    @RequestMapping(value = "/checkDol",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> checkDol(@RequestParam("dolPath")String dolPath) {
        Result<String> result = new Result<String>();
        result.setIsSuccess(true);
        result.setMessages("The dolPath is valid");

        result.setResult("A test sql:select * from ddd;");
        return result;
    }

    @RequestMapping(value = "/importDol",method = RequestMethod.GET)
    @ResponseBody
    public Result moveDol(@RequestParam("dolPath")String path) {
        Result result = new Result();
        Properties p = PropertiesUtil.getProperties("/common.properties");
        String dolBaseDir = p.getProperty("DolBaseDir");
        if (path.charAt(0) != '/') path = "/" + path;
        String dolFullPath  = dolBaseDir + path;
        if (dolService.isExists(dolFullPath)) {
            if (dolService.importDol(dolFullPath)) {
                result.setIsSuccess(true);
            }else result.setIsSuccess(false);
        } else {
            result.setIsSuccess(false);
            result.setMessages("文件不存在");
        }

        return result;
    }
    public static void main(String args[] ) {
        DolController dolController = new DolController();
        dolController.moveDol("dim/schema/dim.com_super_crm_partner.ddl.sql");
    }

}
