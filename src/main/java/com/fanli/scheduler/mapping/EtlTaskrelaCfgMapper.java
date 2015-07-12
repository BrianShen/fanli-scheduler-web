package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.EtlTaskrelaCfg;
import com.fanli.scheduler.entity.EtlTaskrelaCfgExample;
import com.fanli.scheduler.entity.EtlTaskrelaCfgKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EtlTaskrelaCfgMapper {
    int countByExample(EtlTaskrelaCfgExample example);

    int deleteByExample(EtlTaskrelaCfgExample example);

    int deleteByPrimaryKey(EtlTaskrelaCfgKey key);

    int insert(EtlTaskrelaCfg record);

    int insertSelective(EtlTaskrelaCfg record);

    List<EtlTaskrelaCfg> selectByExample(EtlTaskrelaCfgExample example);

    EtlTaskrelaCfg selectByPrimaryKey(EtlTaskrelaCfgKey key);

    int updateByExampleSelective(@Param("record") EtlTaskrelaCfg record, @Param("example") EtlTaskrelaCfgExample example);

    int updateByExample(@Param("record") EtlTaskrelaCfg record, @Param("example") EtlTaskrelaCfgExample example);

    int updateByPrimaryKeySelective(EtlTaskrelaCfg record);

    int updateByPrimaryKey(EtlTaskrelaCfg record);
}