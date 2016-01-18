package com.fanli.scheduler.entity;

import java.util.Date;

public class EtlMonitor {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.name
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.owner_id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private Long ownerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.owner_name
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String ownerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.task_id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private Long taskId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.mobile
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.mail_to
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String mailTo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.mail_cc
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String mailCc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.mail_bcc
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String mailBcc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.mail_subject
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String mailSubject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.mail_message
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String mailMessage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.remark
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.last_log
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private String lastLog;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.last_start_time
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private Date lastStartTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.last_end_time
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private Date lastEndTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.ins_date
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private Date insDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_etl_monitor.upd_date
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    private Date updDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.id
     *
     * @return the value of t_etl_monitor.id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.id
     *
     * @param id the value for t_etl_monitor.id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.name
     *
     * @return the value of t_etl_monitor.name
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.name
     *
     * @param name the value for t_etl_monitor.name
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.owner_id
     *
     * @return the value of t_etl_monitor.owner_id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.owner_id
     *
     * @param ownerId the value for t_etl_monitor.owner_id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.owner_name
     *
     * @return the value of t_etl_monitor.owner_name
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.owner_name
     *
     * @param ownerName the value for t_etl_monitor.owner_name
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.task_id
     *
     * @return the value of t_etl_monitor.task_id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.task_id
     *
     * @param taskId the value for t_etl_monitor.task_id
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.mobile
     *
     * @return the value of t_etl_monitor.mobile
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.mobile
     *
     * @param mobile the value for t_etl_monitor.mobile
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.mail_to
     *
     * @return the value of t_etl_monitor.mail_to
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.mail_to
     *
     * @param mailTo the value for t_etl_monitor.mail_to
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo == null ? null : mailTo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.mail_cc
     *
     * @return the value of t_etl_monitor.mail_cc
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getMailCc() {
        return mailCc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.mail_cc
     *
     * @param mailCc the value for t_etl_monitor.mail_cc
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setMailCc(String mailCc) {
        this.mailCc = mailCc == null ? null : mailCc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.mail_bcc
     *
     * @return the value of t_etl_monitor.mail_bcc
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getMailBcc() {
        return mailBcc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.mail_bcc
     *
     * @param mailBcc the value for t_etl_monitor.mail_bcc
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc == null ? null : mailBcc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.mail_subject
     *
     * @return the value of t_etl_monitor.mail_subject
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getMailSubject() {
        return mailSubject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.mail_subject
     *
     * @param mailSubject the value for t_etl_monitor.mail_subject
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject == null ? null : mailSubject.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.mail_message
     *
     * @return the value of t_etl_monitor.mail_message
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getMailMessage() {
        return mailMessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.mail_message
     *
     * @param mailMessage the value for t_etl_monitor.mail_message
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setMailMessage(String mailMessage) {
        this.mailMessage = mailMessage == null ? null : mailMessage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.remark
     *
     * @return the value of t_etl_monitor.remark
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.remark
     *
     * @param remark the value for t_etl_monitor.remark
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.last_log
     *
     * @return the value of t_etl_monitor.last_log
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public String getLastLog() {
        return lastLog;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.last_log
     *
     * @param lastLog the value for t_etl_monitor.last_log
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setLastLog(String lastLog) {
        this.lastLog = lastLog == null ? null : lastLog.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.last_start_time
     *
     * @return the value of t_etl_monitor.last_start_time
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public Date getLastStartTime() {
        return lastStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.last_start_time
     *
     * @param lastStartTime the value for t_etl_monitor.last_start_time
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setLastStartTime(Date lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.last_end_time
     *
     * @return the value of t_etl_monitor.last_end_time
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public Date getLastEndTime() {
        return lastEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.last_end_time
     *
     * @param lastEndTime the value for t_etl_monitor.last_end_time
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setLastEndTime(Date lastEndTime) {
        this.lastEndTime = lastEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.ins_date
     *
     * @return the value of t_etl_monitor.ins_date
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public Date getInsDate() {
        return insDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.ins_date
     *
     * @param insDate the value for t_etl_monitor.ins_date
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_etl_monitor.upd_date
     *
     * @return the value of t_etl_monitor.upd_date
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public Date getUpdDate() {
        return updDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_etl_monitor.upd_date
     *
     * @param updDate the value for t_etl_monitor.upd_date
     *
     * @mbggenerated Fri Nov 27 17:15:13 CST 2015
     */
    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
}