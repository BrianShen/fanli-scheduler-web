package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.MdsItemtable;
import com.fanli.scheduler.entity.MdsItemtableExample;
import com.fanli.scheduler.entity.MdsItemtableWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdsItemtableMapper {
    int countByExample(MdsItemtableExample example);

    int deleteByExample(MdsItemtableExample example);

    int deleteByPrimaryKey(Integer tableId);

    int insert(MdsItemtableWithBLOBs record);

    int insertSelective(MdsItemtableWithBLOBs record);

    List<MdsItemtableWithBLOBs> selectByExampleWithBLOBs(MdsItemtableExample example);

    List<MdsItemtable> selectByExample(MdsItemtableExample example);

    MdsItemtableWithBLOBs selectByPrimaryKey(Integer tableId);

    int updateByExampleSelective(@Param("record") MdsItemtableWithBLOBs record, @Param("example") MdsItemtableExample example);

    int updateByExampleWithBLOBs(@Param("record") MdsItemtableWithBLOBs record, @Param("example") MdsItemtableExample example);

    int updateByExample(@Param("record") MdsItemtable record, @Param("example") MdsItemtableExample example);

    int updateByPrimaryKeySelective(MdsItemtableWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MdsItemtableWithBLOBs record);

    int updateByPrimaryKey(MdsItemtable record);
}