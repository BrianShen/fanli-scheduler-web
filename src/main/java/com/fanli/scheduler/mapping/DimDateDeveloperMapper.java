package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.DimDateDeveloper;
import com.fanli.scheduler.entity.DimDateDeveloperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DimDateDeveloperMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int countByExample(DimDateDeveloperExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int deleteByExample(DimDateDeveloperExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int insert(DimDateDeveloper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int insertSelective(DimDateDeveloper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    List<DimDateDeveloper> selectByExample(DimDateDeveloperExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    DimDateDeveloper selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int updateByExampleSelective(@Param("record") DimDateDeveloper record, @Param("example") DimDateDeveloperExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int updateByExample(@Param("record") DimDateDeveloper record, @Param("example") DimDateDeveloperExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int updateByPrimaryKeySelective(DimDateDeveloper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dim_data_developer
     *
     * @mbggenerated Fri Jul 31 17:05:43 CST 2015
     */
    int updateByPrimaryKey(DimDateDeveloper record);
}