package com.fanli.scheduler.controller;

import com.fanli.scheduler.entity.EtlTaskrelaStatus;
import com.fanli.scheduler.entity.EtlTaskrelaStatusKey;
import com.fanli.scheduler.mapping.EtlTaskStatusMapper;
import com.fanli.scheduler.mapping.EtlTaskrelaStatusMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wei.shen on 2015/7/11.
 */

@Controller
public class IndexController {
	private static Logger logger = Logger.getLogger(IndexController.class);
	@Autowired
	EtlTaskrelaStatusMapper mapper;

	@RequestMapping(value = "/index" ,method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "index";
	}

	@RequestMapping(value = "/test",method = RequestMethod.GET)
	@ResponseBody
	public EtlTaskrelaStatus getTaskStatus(@RequestParam("taskStatusId")String taskStatusId,@RequestParam("preStatusId")String preStatusId) {
		logger.info("task status id is " + taskStatusId + ", " + "pre status id is " + preStatusId);
		EtlTaskrelaStatusKey etlTaskrelaStatusKey = new EtlTaskrelaStatusKey();
		etlTaskrelaStatusKey.setTaskStatusId(taskStatusId);
		etlTaskrelaStatusKey.setPreStatusId(preStatusId);
		return mapper.selectByPrimaryKey(etlTaskrelaStatusKey);
	}
}