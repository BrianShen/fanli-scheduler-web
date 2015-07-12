package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.EtlTaskStatus;
import com.fanli.scheduler.entity.EtlTaskStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EtlTaskStatusMapper {
    int countByExample(EtlTaskStatusExample example);

    int deleteByExample(EtlTaskStatusExample example);

    int deleteByPrimaryKey(String taskStatusId);

    int insert(EtlTaskStatus record);

    int insertSelective(EtlTaskStatus record);

    List<EtlTaskStatus> selectByExample(EtlTaskStatusExample example);

    EtlTaskStatus selectByPrimaryKey(String taskStatusId);

    int updateByExampleSelective(@Param("record") EtlTaskStatus record, @Param("example") EtlTaskStatusExample example);

    int updateByExample(@Param("record") EtlTaskStatus record, @Param("example") EtlTaskStatusExample example);

    int updateByPrimaryKeySelective(EtlTaskStatus record);

    int updateByPrimaryKey(EtlTaskStatus record);
}