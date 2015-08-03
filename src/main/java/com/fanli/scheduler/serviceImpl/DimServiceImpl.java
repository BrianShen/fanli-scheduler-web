package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.entity.DimDateDeveloper;
import com.fanli.scheduler.entity.DimDateDeveloperExample;
import com.fanli.scheduler.entity.DimTargetHiveDb;
import com.fanli.scheduler.entity.DimTargetHiveDbExample;
import com.fanli.scheduler.mapping.DimDateDeveloperMapper;
import com.fanli.scheduler.mapping.DimTargetHiveDbMapper;
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
    private DimDateDeveloperMapper dimDateDeveloperMapper;

    @Autowired
    private DimTargetHiveDbMapper dimTargetHiveDbMapper;

    @Override
    public List<DimDateDeveloper> getAllDevelopers() {

        return dimDateDeveloperMapper.selectByExample(new DimDateDeveloperExample());
    }

    @Override
    public List<DimTargetHiveDb> getAllTargetDbs() {
        return dimTargetHiveDbMapper.selectByExample(new DimTargetHiveDbExample());
    }
}
