package com.fanli.scheduler.entity;

import java.util.Date;

public class DimTargetHiveDb {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dim_target_hive_db.id
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dim_target_hive_db.name
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dim_target_hive_db.description
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dim_target_hive_db.update_time
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dim_target_hive_db.id
     *
     * @return the value of dim_target_hive_db.id
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dim_target_hive_db.id
     *
     * @param id the value for dim_target_hive_db.id
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dim_target_hive_db.name
     *
     * @return the value of dim_target_hive_db.name
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dim_target_hive_db.name
     *
     * @param name the value for dim_target_hive_db.name
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dim_target_hive_db.description
     *
     * @return the value of dim_target_hive_db.description
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dim_target_hive_db.description
     *
     * @param description the value for dim_target_hive_db.description
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dim_target_hive_db.update_time
     *
     * @return the value of dim_target_hive_db.update_time
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dim_target_hive_db.update_time
     *
     * @param updateTime the value for dim_target_hive_db.update_time
     *
     * @mbggenerated Mon Aug 10 17:49:40 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}