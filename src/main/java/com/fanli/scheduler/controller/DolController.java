package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wei.shen on 2015/7/15.
 */
@Controller
@RequestMapping("/dol")
public class DolController {
    @RequestMapping(value = "/checkDol",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> checkDol(@RequestParam("dolPath")String dolPath) {
        Result<String> result = new Result<String>();
        result.setIsSuccess(true);
        result.setMessages("The dolPath is valid");

        result.setResult("A test sql:select * from ddd;");
        return result;
    }
}
