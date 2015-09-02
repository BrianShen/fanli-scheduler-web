package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by wei.shen on 2015/9/1.
 */
@Controller
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
