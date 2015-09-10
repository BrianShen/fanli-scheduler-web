package com.fanli.scheduler.service;

import com.fanli.scheduler.entity.EtlTaskStatus;
import com.fanli.scheduler.entity.EtlTaskStatusExample;
import com.fanli.scheduler.mapping.EtlTaskStatusMapper;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wei.shen on 2015/7/29.
 */
@Service
public class JobMonitorService {
    @Autowired
    private EtlTaskStatusMapper etlTaskStatusMapper;

    public List<EtlTaskStatus> queryTaskStatuses(String startDate,String endDate,String taskId,String developer,Integer status) {
        List<EtlTaskStatus> list = null;
        EtlTaskStatusExample etlTaskStatusExample = new EtlTaskStatusExample();
        EtlTaskStatusExample.Criteria criteria = etlTaskStatusExample.createCriteria();
        if (StringUtil.isNumeric(taskId)) {
            criteria.andTaskIdEqualTo(Integer.parseInt(taskId));
        } else {
            if (StringUtils.hasLength(taskId))criteria.andTaskNameLike("%" +taskId + "%");
        }

        if (StringUtils.hasLength(developer)) criteria.andOwnerLike("%" + developer + "%");
        if (status != null&& status != 100) {
            if (status == 99) {
                criteria.andStatusNotEqualTo(1);
            } else {
                criteria.andStatusEqualTo(status);
            }

        }
//        if (startDate != null&&endDate!= null) {
//            criteria.andCalDtBetween(startDate,endDate);
//        };
        if (startDate != null&&endDate!= null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date sd = null;
            Date ed = null;
            try {
                sd = simpleDateFormat.parse(startDate);
                ed = simpleDateFormat.parse(endDate);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(ed);
                calendar.add(calendar.DATE,1);
                ed = calendar.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            criteria.andTriggerTimeBetween(sd.getTime(), ed.getTime());
        };
        list = etlTaskStatusMapper.selectByExample(etlTaskStatusExample);
        return list;
    }

    public int handleRerun(String instanceid) {
        EtlTaskStatus taskStatus = new EtlTaskStatus();
        taskStatus.setStatus(0);
        EtlTaskStatusExample etlTaskStatusExample = new EtlTaskStatusExample();
        EtlTaskStatusExample.Criteria criteria = etlTaskStatusExample.createCriteria();
        if (!"".equals(instanceid) && null != instanceid) {
            criteria.andTaskStatusIdEqualTo(instanceid);
        }
        return etlTaskStatusMapper.updateByExampleSelective(taskStatus,etlTaskStatusExample);
    }

    public int handSuccessJob(String instanceid) {
        EtlTaskStatus taskStatus = new EtlTaskStatus();
        taskStatus.setStatus(1);
        EtlTaskStatusExample etlTaskStatusExample = new EtlTaskStatusExample();
        EtlTaskStatusExample.Criteria criteria = etlTaskStatusExample.createCriteria();
        if (!"".equals(instanceid) && null != instanceid) {
            criteria.andTaskStatusIdEqualTo(instanceid);
        }
        return etlTaskStatusMapper.updateByExampleSelective(taskStatus,etlTaskStatusExample);
    }
}
