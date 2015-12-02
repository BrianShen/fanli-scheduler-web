package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.entity.*;
import com.fanli.scheduler.mapping.CtlOwnerInfoMapper;
import com.fanli.scheduler.mapping.DimDateDeveloperMapper;
import com.fanli.scheduler.mapping.DimTargetHiveDbMapper;
import com.fanli.scheduler.mapping.EtlSourceDimMapper;
import com.fanli.scheduler.service.DimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wei.shen on 2015/7/31.
 */

@Service
public class DimServiceImpl implements DimService {

    @Autowired
    private CtlOwnerInfoMapper ctlOwnerInfoMapper;

    @Autowired
    private DimTargetHiveDbMapper dimTargetHiveDbMapper;

    @Autowired
    private EtlSourceDimMapper etlSourceDimMapper;

    @Override
    public List<CtlOwnerInfo> getAllDevelopers() {

        return ctlOwnerInfoMapper.selectByExample(new CtlOwnerInfoExample());
    }

    @Override
    public List<DimTargetHiveDb> getAllTargetDbs() {
        return dimTargetHiveDbMapper.selectByExample(new DimTargetHiveDbExample());
    }

    @Override
    public List<EtlSourceDim> getSourceByType(String type) {
        EtlSourceDimExample etlSourceDimExample = new EtlSourceDimExample();
        EtlSourceDimExample.Criteria criteria= etlSourceDimExample.or();
        if (type != null && !"".equals(type)) criteria.andSourceTypeEqualTo(type);
        return etlSourceDimMapper.selectByExample(etlSourceDimExample);
    }
}
