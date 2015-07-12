package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.EtlTaskrelaStatus;
import com.fanli.scheduler.entity.EtlTaskrelaStatusExample;
import com.fanli.scheduler.entity.EtlTaskrelaStatusKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EtlTaskrelaStatusMapper {
    int countByExample(EtlTaskrelaStatusExample example);

    int deleteByExample(EtlTaskrelaStatusExample example);

    int deleteByPrimaryKey(EtlTaskrelaStatusKey key);

    int insert(EtlTaskrelaStatus record);

    int insertSelective(EtlTaskrelaStatus record);

    List<EtlTaskrelaStatus> selectByExample(EtlTaskrelaStatusExample example);

    EtlTaskrelaStatus selectByPrimaryKey(EtlTaskrelaStatusKey key);

    int updateByExampleSelective(@Param("record") EtlTaskrelaStatus record, @Param("example") EtlTaskrelaStatusExample example);

    int updateByExample(@Param("record") EtlTaskrelaStatus record, @Param("example") EtlTaskrelaStatusExample example);

    int updateByPrimaryKeySelective(EtlTaskrelaStatus record);

    int updateByPrimaryKey(EtlTaskrelaStatus record);
}