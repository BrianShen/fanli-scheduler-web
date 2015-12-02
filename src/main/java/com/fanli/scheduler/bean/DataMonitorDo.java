package com.fanli.scheduler.bean;

/**
 * Created by wei.shen on 2015/11/25.
 */
public class DataMonitorDo {
    private Long detailId;
    private Long ownerId;
    private String ownerName;
    private String title;
    private Long taskId;
    private String mobile;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getOwnerId() {

        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    private String mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    private String mailMessage;
    private String sourceDatasource;
    private String targetDatasource;
    private String db;
    private String sourceSql;
    private String targetSql;
    private String contrast;

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "DataMonitorDo{" +
                "ownerName='" + ownerName + '\'' +
                ", title='" + title + '\'' +
                ", taskId=" + taskId +
                ", mobile='" + mobile + '\'' +
                ", mailTo='" + mailTo + '\'' +
                ", mailCc='" + mailCc + '\'' +
                ", mailBcc='" + mailBcc + '\'' +
                ", mailSubject='" + mailSubject + '\'' +
                ", mailMessage='" + mailMessage + '\'' +
                ", sourceDatasource='" + sourceDatasource + '\'' +
                ", targetDatasource='" + targetDatasource + '\'' +
                ", db='" + db + '\'' +
                ", sourceSql='" + sourceSql + '\'' +
                ", targetSql='" + targetSql + '\'' +
                ", contrast='" + contrast + '\'' +
                '}';
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(String mailMessage) {
        this.mailMessage = mailMessage;
    }

    public String getSourceDatasource() {
        return sourceDatasource;
    }

    public void setSourceDatasource(String sourceDatasource) {
        this.sourceDatasource = sourceDatasource;
    }

    public String getTargetDatasource() {
        return targetDatasource;
    }

    public void setTargetDatasource(String targetDatasource) {
        this.targetDatasource = targetDatasource;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getSourceSql() {
        return sourceSql;
    }

    public void setSourceSql(String sourceSql) {
        this.sourceSql = sourceSql;
    }

    public String getTargetSql() {
        return targetSql;
    }

    public void setTargetSql(String targetSql) {
        this.targetSql = targetSql;
    }

    public String getContrast() {
        return contrast;
    }

    public void setContrast(String contrast) {
        this.contrast = contrast;
    }
}
