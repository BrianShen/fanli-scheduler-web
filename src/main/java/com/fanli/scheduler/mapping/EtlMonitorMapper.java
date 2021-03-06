package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.EtlMonitor;
import com.fanli.scheduler.entity.EtlMonitorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EtlMonitorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int countByExample(EtlMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int deleteByExample(EtlMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int insert(EtlMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int insertSelective(EtlMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    List<EtlMonitor> selectByExample(EtlMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    EtlMonitor selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int updateByExampleSelective(@Param("record") EtlMonitor record, @Param("example") EtlMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int updateByExample(@Param("record") EtlMonitor record, @Param("example") EtlMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int updateByPrimaryKeySelective(EtlMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_etl_monitor
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    int updateByPrimaryKey(EtlMonitor record);
}