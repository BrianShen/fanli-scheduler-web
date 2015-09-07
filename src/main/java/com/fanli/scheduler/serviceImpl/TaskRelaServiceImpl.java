package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.entity.EtlTaskrelaCfg;
import com.fanli.scheduler.entity.EtlTaskrelaCfgExample;
import com.fanli.scheduler.mapping.EtlTaskrelaCfgMapper;
import com.fanli.scheduler.service.TaskRelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei.shen on 2015/8/31.
 */
@Service
public class TaskRelaServiceImpl implements TaskRelaService {
    @Autowired
    private EtlTaskrelaCfgMapper etlTaskrelaCfgMapper;

    @Override
    public List<Integer> getRelaChildTaskByTaskid(Integer taskid) {
        EtlTaskrelaCfgExample example = new EtlTaskrelaCfgExample();
        EtlTaskrelaCfgExample.Criteria criteria = example.createCriteria();
        if (taskid != null) {
            criteria.andPreIdEqualTo(taskid);
        }
        List<EtlTaskrelaCfg> list = etlTaskrelaCfgMapper.selectByExample(example);
        List<Integer> ret = new ArrayList<Integer>();
        for (EtlTaskrelaCfg cfg :list) {
            ret.add(cfg.getTaskId());
        }
        return ret;
    }
}
