package com.fanli.scheduler.service;

import com.fanli.scheduler.entity.CtlOwnerInfo;
import com.fanli.scheduler.entity.DimDateDeveloper;
import com.fanli.scheduler.entity.DimTargetHiveDb;
import com.fanli.scheduler.entity.EtlSourceDim;

import java.util.List;

/**
 * Created by wei.shen on 2015/7/31.
 */
public interface DimService {
    List<CtlOwnerInfo> getAllDevelopers();
    List<DimTargetHiveDb> getAllTargetDbs();
    List<EtlSourceDim> getSourceByType(String type);
}
