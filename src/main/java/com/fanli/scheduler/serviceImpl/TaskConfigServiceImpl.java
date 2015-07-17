package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.mapping.EtlTaskCfgMapper;
import com.fanli.scheduler.service.TaskConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
