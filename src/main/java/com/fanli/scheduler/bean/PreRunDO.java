package com.fanli.scheduler.bean;


import com.fanli.scheduler.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PreRunDO implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(PreRunDO.class);

    private Date startDate;
    private Date endDate;
    private List<Integer> taskIds;

    public PreRunDO() {
    }

    public PreRunDO(Integer taskId, String startDate, String endDate, String committer) {
        taskIds = new ArrayList<Integer>();
        taskIds.add(taskId);
        this.startDate = CommonUtils.strToDate(startDate);
        this.endDate = CommonUtils.strToDate(endDate);
    }


    public List<Integer> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<Integer> taskIds) {
        this.taskIds = taskIds;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isValid() {
        if (taskIds == null || startDate == null || endDate == null) {
            logger.error("prerun task error, one of the parameters(taskId, begin, end) is null");
            return false;
        }
        if (startDate.after(endDate)) {
            logger.error("prerun task error, startDate must less than endDate!");
            return false;
        }
        Date triggerDate = new Date();
        if (startDate.after(triggerDate)) {
            logger.error("prerun task error, startDate must less than today!");
            return false;
        }
        return true;
    }

}
