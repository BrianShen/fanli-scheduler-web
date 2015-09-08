package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.EtlLoadCfg;
import com.fanli.scheduler.entity.EtlLoadCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EtlLoadCfgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table etl_load_cfg
     *
     * @mbggenerated Wed Aug 12 09:21:39 CST 2015
     */
    int countByExample(EtlLoadCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table etl_load_cfg
     *
     * @mbggenerated Wed Aug 12 09:21:39 CST 2015
     */
    int deleteByExample(EtlLoadCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table etl_load_cfg
     *
     * @mbggenerated Wed Aug 12 09:21:39 CST 2015
     */
    int insert(EtlLoadCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table etl_load_cfg
     *
     * @mbggenerated Wed Aug 12 09:21:39 CST 2015
     */
    int insertSelective(EtlLoadCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table etl_load_cfg
     *
     * @mbggenerated Wed Aug 12 09:21:39 CST 2015
     */
    List<EtlLoadCfg> selectByExample(EtlLoadCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table etl_load_cfg
     *
     * @mbggenerated Wed Aug 12 09:21:39 CST 2015
     */
    int updateByExampleSelective(@Param("record") EtlLoadCfg record, @Param("example") EtlLoadCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table etl_load_cfg
     *
     * @mbggenerated Wed Aug 12 09:21:39 CST 2015
     */
    int updateByExample(@Param("record") EtlLoadCfg record, @Param("example") EtlLoadCfgExample example);
}