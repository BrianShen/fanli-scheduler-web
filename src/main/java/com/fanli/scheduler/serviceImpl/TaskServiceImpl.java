package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.entity.EtlTaskStatus;
import com.fanli.scheduler.mapping.EtlTaskStatusMapper;
import com.fanli.scheduler.service.InstanceService;
import com.fanli.scheduler.service.TaskService;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wei.shen on 2015/8/21.
 */
@Service
public class TaskServiceImpl  implements TaskService {
    private static Logger logger = Logger.getLogger(TaskServiceImpl.class);
    @Autowired
    private EtlTaskCfgMapper etlTaskCfgMapper;

    @Autowired
    private EtlTaskStatusMapper etlTaskStatusMapper;

    @Autowired
    private InstanceService instanceService;

    @Override
    public List<Integer> getChildrenByTaskid(Integer taskid) {
        return null;
    }

    @Override
    public Map<String,Object> generateInstance(String start, String end, Integer taskid) {
        Map<String,Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EtlTaskCfg etlTaskCfg = etlTaskCfgMapper.selectByPrimaryKey(taskid);
        String freq = etlTaskCfg.getFreq();
        CronExpression ce = null;
        try {
            ce = new CronExpression(freq);
        } catch (ParseException e) {
            logger.error("prerun task errors, freq is illegal for cronExpression, cronExpression: " + freq);
            return null;
        }
        Date triggerDate = new Date();
        if (endDate.after(triggerDate)) {
            endDate = triggerDate;
        }
        int num = 0;
        List<String> list = new ArrayList<String>();
        while (true) {
            beginDate = ce.getNextValidTimeAfter(beginDate);
            if (beginDate.after(endDate)) break;
            EtlTaskStatus etlTaskStatus = instanceService.generateInstances(beginDate,triggerDate,etlTaskCfg);
            list.add(etlTaskStatus.getTaskStatusId());
            logger.info(etlTaskStatus);
            try {
                if (etlTaskStatusMapper.insert(etlTaskStatus) == 1) {
                    num ++;
                }
            } catch (Exception e) {
                return null;
            }
        }
        logger.info("successlly generate " + num + " instances of " + taskid );
        map.put("num",num);
        map.put("instanceids",list);
        return map;
    }

    @Override
    public Boolean invalidTask(List<Integer> taskids,Date updateTime) {
        assert taskids != null;
        int num = 0;
        for(int id :taskids) {
            EtlTaskCfg cfg = new EtlTaskCfg();
            cfg.setTaskId(id);
            cfg.setIfEnable(0);
            cfg.setUpdateTime(updateTime);
            num += etlTaskCfgMapper.updateByPrimaryKeySelective(cfg);
        }
        logger.info(num + " tasks are set to invalid tasks,the total tasks to set is " + taskids.size());

        if (num == taskids.size()) return true;
        else return false;
    }

    @Override
    public Boolean validateTask(Integer taskid,Date updateTime) {
        assert taskid != null;
        EtlTaskCfg cfg = new EtlTaskCfg();
        cfg.setTaskId(taskid);
        cfg.setIfEnable(1);
        cfg.setUpdateTime(updateTime);
        int ret = etlTaskCfgMapper.updateByPrimaryKeySelective(cfg);
        if (ret == 1) {
            return true;
        } else return false;
    }


}
