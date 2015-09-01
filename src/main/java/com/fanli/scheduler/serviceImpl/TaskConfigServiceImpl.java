package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.bean.TaskQuery;
import com.fanli.scheduler.entity.*;
import com.fanli.scheduler.mapping.EtlTaskCfgMapper;
import com.fanli.scheduler.mapping.EtlTaskrelaCfgMapper;
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

    @Autowired
    public EtlTaskrelaCfgMapper etlTaskrelaCfgMapper;
    @Override
    public int insertTaskConfig(EtlTaskCfg etlTaskCfg) {
        logger.info("insert etlTaskCfg into database:" + etlTaskCfg.getTaskName());
        return etlTaskCfgMapper.insert(etlTaskCfg);
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
        if (taskQuery.getTaskGroupId() != null) criteria.andTaskGroupIdEqualTo(taskQuery.getTaskGroupId());
        if (!taskQuery.getOwner().equals("")) criteria.andOwnerEqualTo(taskQuery.getOwner());
        if (taskQuery.getTaskId() != null) criteria.andTaskIdEqualTo(taskQuery.getTaskId());
        if (taskQuery.getIsValid() != null) criteria.andIfEnableEqualTo(taskQuery.getIsValid());
        return etlTaskCfgMapper.selectByExample(etlTaskCfgExample);
    }

    @Override
    public int insertTaskRela(EtlTaskrelaCfg etlTaskrelaCfg) {

        return etlTaskrelaCfgMapper.insert(etlTaskrelaCfg);
    }

    @Override
    public int updateTask(EtlTaskCfg etlTaskCfg) {
        return etlTaskCfgMapper.updateByPrimaryKeySelective(etlTaskCfg);
    }

    @Override
    public int deleteTaskRela(Integer taskid) {
        EtlTaskrelaCfgExample etlTaskrelaCfgExample = new EtlTaskrelaCfgExample();
        EtlTaskrelaCfgExample.Criteria criteria = etlTaskrelaCfgExample.createCriteria();
        if (taskid != null) criteria.andTaskIdEqualTo(taskid);
        return etlTaskrelaCfgMapper.deleteByExample(etlTaskrelaCfgExample);
    }

    @Override
    public int insertTransferTask(EtlTaskCfg etlTaskCfg) {
        return etlTaskCfgMapper.insert(etlTaskCfg);
    }

    @Override
    public EtlTaskCfg getTransferById(Integer taskid) {
        if (taskid == null) return null;
        return etlTaskCfgMapper.selectByPrimaryKey(taskid);
    }

    @Override
    public int updateTransfer(EtlTaskCfg etlTaskCfg) {

        return etlTaskCfgMapper.updateByPrimaryKeySelective(etlTaskCfg);
    }


}
