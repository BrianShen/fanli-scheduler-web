package com.fanli.scheduler.entity;

import java.util.Date;

public class EtlSourceDim {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_source_dim.source_id
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    private Integer sourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_source_dim.source_domain
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    private String sourceDomain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_source_dim.connect_property
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    private String connectProperty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_source_dim.create_time
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_source_dim.update_time
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_source_dim.is_valid
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    private Integer isValid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_source_dim.source_type
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    private String sourceType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_source_dim.source_id
     *
     * @return the value of etl_source_dim.source_id
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_source_dim.source_id
     *
     * @param sourceId the value for etl_source_dim.source_id
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_source_dim.source_domain
     *
     * @return the value of etl_source_dim.source_domain
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public String getSourceDomain() {
        return sourceDomain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_source_dim.source_domain
     *
     * @param sourceDomain the value for etl_source_dim.source_domain
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public void setSourceDomain(String sourceDomain) {
        this.sourceDomain = sourceDomain == null ? null : sourceDomain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_source_dim.connect_property
     *
     * @return the value of etl_source_dim.connect_property
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public String getConnectProperty() {
        return connectProperty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_source_dim.connect_property
     *
     * @param connectProperty the value for etl_source_dim.connect_property
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public void setConnectProperty(String connectProperty) {
        this.connectProperty = connectProperty == null ? null : connectProperty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_source_dim.create_time
     *
     * @return the value of etl_source_dim.create_time
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_source_dim.create_time
     *
     * @param createTime the value for etl_source_dim.create_time
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_source_dim.update_time
     *
     * @return the value of etl_source_dim.update_time
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_source_dim.update_time
     *
     * @param updateTime the value for etl_source_dim.update_time
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_source_dim.is_valid
     *
     * @return the value of etl_source_dim.is_valid
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_source_dim.is_valid
     *
     * @param isValid the value for etl_source_dim.is_valid
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_source_dim.source_type
     *
     * @return the value of etl_source_dim.source_type
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_source_dim.source_type
     *
     * @param sourceType the value for etl_source_dim.source_type
     *
     * @mbggenerated Tue Aug 11 14:18:13 CST 2015
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }
}