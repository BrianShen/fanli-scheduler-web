package com.fanli.scheduler.controller;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.EtlSystemFeedback;
import com.fanli.scheduler.mapping.EtlSystemFeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by wei.shen on 2015/10/10.
 */
@RequestMapping("/feedbacks")
@Controller
public class FeedbackController {
    @Autowired
    private EtlSystemFeedbackMapper etlSystemFeedbackMapper;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Result<EtlSystemFeedback> getFeedbacks() throws Exception{
        Result<EtlSystemFeedback> result = new Result<EtlSystemFeedback>();
        List<EtlSystemFeedback> list = etlSystemFeedbackMapper.listAllFeedbacks();
        if (list != null) {
            result.setIsSuccess(true);
            result.setResults(list);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody EtlSystemFeedback addFeedback(@RequestBody EtlSystemFeedback feedback) throws Exception{
        Date date = new Date();
        feedback.setAddTime(date);
        feedback.setUpdateTime(date);
        etlSystemFeedbackMapper.insert(feedback);
        return feedback;
    }
}
