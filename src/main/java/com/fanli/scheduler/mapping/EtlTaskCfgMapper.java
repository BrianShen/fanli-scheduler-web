package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.EtlTaskCfg;
import com.fanli.scheduler.entity.EtlTaskCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EtlTaskCfgMapper {
    int countByExample(EtlTaskCfgExample example);

    int deleteByExample(EtlTaskCfgExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(EtlTaskCfg record);

    int insertSelective(EtlTaskCfg record);

    List<EtlTaskCfg> selectByExample(EtlTaskCfgExample example);

    EtlTaskCfg selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") EtlTaskCfg record, @Param("example") EtlTaskCfgExample example);

    int updateByExample(@Param("record") EtlTaskCfg record, @Param("example") EtlTaskCfgExample example);

    int updateByPrimaryKeySelective(EtlTaskCfg record);

    int updateByPrimaryKey(EtlTaskCfg record);
}