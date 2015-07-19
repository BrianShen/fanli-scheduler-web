package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.bean.TaskQuery;
import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.entity.EtlTaskCfgExample;
import com.fanli.scheduler.mapping.EtlTaskCfgMapper;
import com.fanli.scheduler.service.TaskConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wei.shen on 2015/7/15.
 */
@Service
public class TaskConfigServiceImpl implements TaskConfigService{
    private static Logger logger = Logger.getLogger(TaskConfigServiceImpl.class);
    @Autowired
    public EtlTaskCfgMapper etlTaskCfgMapper;
    @Override
    public int insertTaskConfig(EtlTaskCfg etlTaskCfg) {
        logger.info("insert etlTaskCfg into database:" + etlTaskCfg.getTaskName());
        etlTaskCfgMapper.insert(etlTaskCfg);
        return 0;
    }

    @Override
    public EtlTaskCfg getTaskById(Integer id) {
        if (id == null) return null;
        return etlTaskCfgMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EtlTaskCfg> getTaskByParams(TaskQuery taskQuery) {
        EtlTaskCfgExample etlTaskCfgExample = new EtlTaskCfgExample();
        EtlTaskCfgExample.Criteria criteria = etlTaskCfgExample.createCriteria();
        System.out.println(taskQuery);
        if (taskQuery.getTaskGroupId() != null) criteria.andTaskGroupIdEqualTo(taskQuery.getTaskGroupId());
        if (!taskQuery.getOwner().equals("")) criteria.andOwnerEqualTo(taskQuery.getOwner());
        if (taskQuery.getTaskId() != null) criteria.andTaskIdEqualTo(taskQuery.getTaskId());
        return etlTaskCfgMapper.selectByExample(etlTaskCfgExample);
    }
}
