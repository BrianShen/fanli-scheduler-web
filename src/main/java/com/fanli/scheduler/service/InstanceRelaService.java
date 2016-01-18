package com.fanli.scheduler.service;

import com.fanli.scheduler.bean.InstanceRelaNode;
import com.fanli.scheduler.bean.JobStatus;
import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.EtlTaskStatus;
import com.fanli.scheduler.entity.EtlTaskrelaStatus;
import com.fanli.scheduler.entity.EtlTaskrelaStatusExample;
import com.fanli.scheduler.exception.ParameterInvalidException;
import com.fanli.scheduler.mapping.EtlTaskStatusMapper;
import com.fanli.scheduler.mapping.EtlTaskrelaStatusMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wei.shen on 2015/9/8.
 */
@Service
public class InstanceRelaService {
    @Autowired
    private EtlTaskrelaStatusMapper etlTaskrelaStatusMapper;

    @Autowired
    private EtlTaskStatusMapper etlTaskStatusMapper;

    private static Logger logger = Logger.getLogger(InstanceRelaService.class);

    @Autowired
    private TaskConfigService taskConfigService;

    public Result<EtlTaskrelaStatus> handleGetDirectInfluencedInstancesByInstanceId(String instanceid) throws ParameterInvalidException {
        if (!StringUtils.hasLength(instanceid)) {
            logger.error("invalid param!");
            throw new ParameterInvalidException("instanceid could not be null or empty");
        }
        Result result = new Result();
        EtlTaskrelaStatusExample example = new EtlTaskrelaStatusExample();
        EtlTaskrelaStatusExample.Criteria criteria = example.createCriteria();
        criteria.andPreStatusIdEqualTo(instanceid);
        List<EtlTaskrelaStatus> list = etlTaskrelaStatusMapper.selectByExample(example);
        //logger.info("The influenced instances are :" + list);
        result.setResults(list);
        result.setIsSuccess(true);
        result.setMessages("");
        return result;
    }

    public InstanceRelaNode handleGetRelaInstanceTreeStatusByInstanceId(String instanceid) {
        InstanceRelaNode node = new InstanceRelaNode();

        EtlTaskrelaStatusExample example = new EtlTaskrelaStatusExample();
        EtlTaskrelaStatusExample.Criteria criteria = example.createCriteria();
        criteria.andTaskStatusIdEqualTo(instanceid);
        List<EtlTaskrelaStatus> list = etlTaskrelaStatusMapper.selectByExample(example);
        EtlTaskStatus self = etlTaskStatusMapper.selectByPrimaryKey(instanceid);

        JobStatus jobStatus = new JobStatus();
        jobStatus.setTaskId(self.getTaskId());
        jobStatus.setInstanceId(self.getTaskStatusId());
        jobStatus.setStatus(self.getStatus());
        jobStatus.setTaskName(self.getTaskName());
        node.setSelf(jobStatus);

        if (list.size() == 0) {
            node.setParent(null);
            return node;
        }

        List<InstanceRelaNode> parent = new ArrayList<InstanceRelaNode>();
        for (EtlTaskrelaStatus taskrelaStatus:list) {
            InstanceRelaNode instanceRelaNode = handleGetRelaInstanceTreeStatusByInstanceId(taskrelaStatus.getPreStatusId());
            parent.add(instanceRelaNode);
        }
        node.setParent(parent);
        return node;
    }

}
