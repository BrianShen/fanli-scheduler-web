package com.fanli.scheduler.entity;

import java.util.Date;

public class EtlMetaColumn extends EtlMetaColumnKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.table_id
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Integer tableId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.column_name
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private String columnName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.column_index
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Integer columnIndex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.column_type
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private String columnType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.column_comment
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private String columnComment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.default_value
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private String defaultValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.column_access_code
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Integer columnAccessCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.is_primary
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Byte isPrimary;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.is_foreign
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Byte isForeign;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.is_partition
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Byte isPartition;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.hot_level
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Integer hotLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.add_time
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.update_time
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column etl_meta_column.last_update_user
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    private String lastUpdateUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.table_id
     *
     * @return the value of etl_meta_column.table_id
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Integer getTableId() {
        return tableId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.table_id
     *
     * @param tableId the value for etl_meta_column.table_id
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.column_name
     *
     * @return the value of etl_meta_column.column_name
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.column_name
     *
     * @param columnName the value for etl_meta_column.column_name
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.column_index
     *
     * @return the value of etl_meta_column.column_index
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Integer getColumnIndex() {
        return columnIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.column_index
     *
     * @param columnIndex the value for etl_meta_column.column_index
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.column_type
     *
     * @return the value of etl_meta_column.column_type
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public String getColumnType() {
        return columnType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.column_type
     *
     * @param columnType the value for etl_meta_column.column_type
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.column_comment
     *
     * @return the value of etl_meta_column.column_comment
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public String getColumnComment() {
        return columnComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.column_comment
     *
     * @param columnComment the value for etl_meta_column.column_comment
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment == null ? null : columnComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.default_value
     *
     * @return the value of etl_meta_column.default_value
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.default_value
     *
     * @param defaultValue the value for etl_meta_column.default_value
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.column_access_code
     *
     * @return the value of etl_meta_column.column_access_code
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Integer getColumnAccessCode() {
        return columnAccessCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.column_access_code
     *
     * @param columnAccessCode the value for etl_meta_column.column_access_code
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setColumnAccessCode(Integer columnAccessCode) {
        this.columnAccessCode = columnAccessCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.is_primary
     *
     * @return the value of etl_meta_column.is_primary
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Byte getIsPrimary() {
        return isPrimary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.is_primary
     *
     * @param isPrimary the value for etl_meta_column.is_primary
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setIsPrimary(Byte isPrimary) {
        this.isPrimary = isPrimary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.is_foreign
     *
     * @return the value of etl_meta_column.is_foreign
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Byte getIsForeign() {
        return isForeign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.is_foreign
     *
     * @param isForeign the value for etl_meta_column.is_foreign
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setIsForeign(Byte isForeign) {
        this.isForeign = isForeign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.is_partition
     *
     * @return the value of etl_meta_column.is_partition
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Byte getIsPartition() {
        return isPartition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.is_partition
     *
     * @param isPartition the value for etl_meta_column.is_partition
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setIsPartition(Byte isPartition) {
        this.isPartition = isPartition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.hot_level
     *
     * @return the value of etl_meta_column.hot_level
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Integer getHotLevel() {
        return hotLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.hot_level
     *
     * @param hotLevel the value for etl_meta_column.hot_level
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setHotLevel(Integer hotLevel) {
        this.hotLevel = hotLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.add_time
     *
     * @return the value of etl_meta_column.add_time
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.add_time
     *
     * @param addTime the value for etl_meta_column.add_time
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.update_time
     *
     * @return the value of etl_meta_column.update_time
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.update_time
     *
     * @param updateTime the value for etl_meta_column.update_time
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column etl_meta_column.last_update_user
     *
     * @return the value of etl_meta_column.last_update_user
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column etl_meta_column.last_update_user
     *
     * @param lastUpdateUser the value for etl_meta_column.last_update_user
     *
     * @mbggenerated Fri Oct 09 11:14:50 CST 2015
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }
}