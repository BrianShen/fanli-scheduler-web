package com.fanli.scheduler.serviceImpl;

import com.fanli.scheduler.entity.MdsItemtableWithBLOBs;
import com.fanli.scheduler.mapping.MdsItemtableMapper;
import com.fanli.scheduler.service.MetaDataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wei.shen on 2015/7/24.
 */
@Service
public class MetaDataManageServiceImpl implements MetaDataManagerService {

    @Autowired
    private MdsItemtableMapper mdsItemtableMapper;
    @Override
    public int addConfigedMeta(MdsItemtableWithBLOBs mdsItemtableWithBLOBs) {
        if (mdsItemtableWithBLOBs == null) return 0;
        return mdsItemtableMapper.insert(mdsItemtableWithBLOBs);
    }
}
