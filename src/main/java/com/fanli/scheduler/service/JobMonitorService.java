package com.fanli.scheduler.service;

import com.fanli.scheduler.entity.EtlTaskStatus;
import com.fanli.scheduler.entity.EtlTaskStatusExample;
import com.fanli.scheduler.mapping.EtlTaskStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wei.shen on 2015/7/29.
 */
@Service
public class JobMonitorService {
    @Autowired
    private EtlTaskStatusMapper etlTaskStatusMapper;

    public List<EtlTaskStatus> queryTaskStatuses(String startDate,String endDate,Integer taskId,String developer,Integer status) {
        List<EtlTaskStatus> list = null;
        EtlTaskStatusExample etlTaskStatusExample = new EtlTaskStatusExample();
        EtlTaskStatusExample.Criteria criteria = etlTaskStatusExample.createCriteria();
        if (taskId != null && taskId != 100) criteria.andTaskIdEqualTo(taskId);
        if (!developer.equals("")) criteria.andOwnerEqualTo(developer);
        if (status != null) criteria.andStatusEqualTo(status);
        if (startDate != null&&endDate!= null) criteria.andCalDtBetween(startDate,endDate);
        list = etlTaskStatusMapper.selectByExample(etlTaskStatusExample);
        return list;
    }
}
