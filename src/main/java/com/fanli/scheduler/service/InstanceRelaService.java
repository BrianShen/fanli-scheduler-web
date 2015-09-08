package com.fanli.scheduler.service;

import com.fanli.scheduler.bean.Result;
import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.entity.EtlTaskrelaStatus;
import com.fanli.scheduler.entity.EtlTaskrelaStatusExample;
import com.fanli.scheduler.exception.ParameterInvalidException;
import com.fanli.scheduler.mapping.EtlTaskrelaStatusMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by wei.shen on 2015/9/8.
 */
@Service
public class InstanceRelaService {
    @Autowired
    private EtlTaskrelaStatusMapper etlTaskrelaStatusMapper;

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
        logger.info("The influenced instances are :" + list);
        result.setResults(list);
        result.setIsSuccess(true);
        result.setMessages("");
        return result;
    }

}
