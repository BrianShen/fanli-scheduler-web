package com.fanli.scheduler.service;

import com.fanli.scheduler.entity.DimDateDeveloper;
import com.fanli.scheduler.entity.DimTargetHiveDb;

import java.util.List;

/**
 * Created by wei.shen on 2015/7/31.
 */
public interface DimService {
    List<DimDateDeveloper> getAllDevelopers();
    List<DimTargetHiveDb> getAllTargetDbs();
}
