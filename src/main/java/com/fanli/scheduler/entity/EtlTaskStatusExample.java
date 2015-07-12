package com.fanli.scheduler.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EtlTaskStatusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EtlTaskStatusExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTaskStatusIdIsNull() {
            addCriterion("task_status_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdIsNotNull() {
            addCriterion("task_status_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdEqualTo(String value) {
            addCriterion("task_status_id =", value, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdNotEqualTo(String value) {
            addCriterion("task_status_id <>", value, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdGreaterThan(String value) {
            addCriterion("task_status_id >", value, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_status_id >=", value, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdLessThan(String value) {
            addCriterion("task_status_id <", value, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdLessThanOrEqualTo(String value) {
            addCriterion("task_status_id <=", value, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdLike(String value) {
            addCriterion("task_status_id like", value, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdNotLike(String value) {
            addCriterion("task_status_id not like", value, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdIn(List<String> values) {
            addCriterion("task_status_id in", values, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdNotIn(List<String> values) {
            addCriterion("task_status_id not in", values, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdBetween(String value1, String value2) {
            addCriterion("task_status_id between", value1, value2, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIdNotBetween(String value1, String value2) {
            addCriterion("task_status_id not between", value1, value2, "taskStatusId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("task_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("task_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("task_name =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("task_name <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("task_name >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_name >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("task_name <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("task_name <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("task_name not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("task_name in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("task_name not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("task_name between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("task_name not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdIsNull() {
            addCriterion("task_group_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdIsNotNull() {
            addCriterion("task_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdEqualTo(Integer value) {
            addCriterion("task_group_id =", value, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdNotEqualTo(Integer value) {
            addCriterion("task_group_id <>", value, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdGreaterThan(Integer value) {
            addCriterion("task_group_id >", value, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_group_id >=", value, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdLessThan(Integer value) {
            addCriterion("task_group_id <", value, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_group_id <=", value, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdIn(List<Integer> values) {
            addCriterion("task_group_id in", values, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdNotIn(List<Integer> values) {
            addCriterion("task_group_id not in", values, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("task_group_id between", value1, value2, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andTaskGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_group_id not between", value1, value2, "taskGroupId");
            return (Criteria) this;
        }

        public Criteria andResourceIsNull() {
            addCriterion("resource is null");
            return (Criteria) this;
        }

        public Criteria andResourceIsNotNull() {
            addCriterion("resource is not null");
            return (Criteria) this;
        }

        public Criteria andResourceEqualTo(String value) {
            addCriterion("resource =", value, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceNotEqualTo(String value) {
            addCriterion("resource <>", value, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceGreaterThan(String value) {
            addCriterion("resource >", value, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceGreaterThanOrEqualTo(String value) {
            addCriterion("resource >=", value, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceLessThan(String value) {
            addCriterion("resource <", value, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceLessThanOrEqualTo(String value) {
            addCriterion("resource <=", value, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceLike(String value) {
            addCriterion("resource like", value, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceNotLike(String value) {
            addCriterion("resource not like", value, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceIn(List<String> values) {
            addCriterion("resource in", values, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceNotIn(List<String> values) {
            addCriterion("resource not in", values, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceBetween(String value1, String value2) {
            addCriterion("resource between", value1, value2, "resource");
            return (Criteria) this;
        }

        public Criteria andResourceNotBetween(String value1, String value2) {
            addCriterion("resource not between", value1, value2, "resource");
            return (Criteria) this;
        }

        public Criteria andCommandIsNull() {
            addCriterion("command is null");
            return (Criteria) this;
        }

        public Criteria andCommandIsNotNull() {
            addCriterion("command is not null");
            return (Criteria) this;
        }

        public Criteria andCommandEqualTo(String value) {
            addCriterion("command =", value, "command");
            return (Criteria) this;
        }

        public Criteria andCommandNotEqualTo(String value) {
            addCriterion("command <>", value, "command");
            return (Criteria) this;
        }

        public Criteria andCommandGreaterThan(String value) {
            addCriterion("command >", value, "command");
            return (Criteria) this;
        }

        public Criteria andCommandGreaterThanOrEqualTo(String value) {
            addCriterion("command >=", value, "command");
            return (Criteria) this;
        }

        public Criteria andCommandLessThan(String value) {
            addCriterion("command <", value, "command");
            return (Criteria) this;
        }

        public Criteria andCommandLessThanOrEqualTo(String value) {
            addCriterion("command <=", value, "command");
            return (Criteria) this;
        }

        public Criteria andCommandLike(String value) {
            addCriterion("command like", value, "command");
            return (Criteria) this;
        }

        public Criteria andCommandNotLike(String value) {
            addCriterion("command not like", value, "command");
            return (Criteria) this;
        }

        public Criteria andCommandIn(List<String> values) {
            addCriterion("command in", values, "command");
            return (Criteria) this;
        }

        public Criteria andCommandNotIn(List<String> values) {
            addCriterion("command not in", values, "command");
            return (Criteria) this;
        }

        public Criteria andCommandBetween(String value1, String value2) {
            addCriterion("command between", value1, value2, "command");
            return (Criteria) this;
        }

        public Criteria andCommandNotBetween(String value1, String value2) {
            addCriterion("command not between", value1, value2, "command");
            return (Criteria) this;
        }

        public Criteria andLogPathIsNull() {
            addCriterion("log_path is null");
            return (Criteria) this;
        }

        public Criteria andLogPathIsNotNull() {
            addCriterion("log_path is not null");
            return (Criteria) this;
        }

        public Criteria andLogPathEqualTo(String value) {
            addCriterion("log_path =", value, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathNotEqualTo(String value) {
            addCriterion("log_path <>", value, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathGreaterThan(String value) {
            addCriterion("log_path >", value, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathGreaterThanOrEqualTo(String value) {
            addCriterion("log_path >=", value, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathLessThan(String value) {
            addCriterion("log_path <", value, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathLessThanOrEqualTo(String value) {
            addCriterion("log_path <=", value, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathLike(String value) {
            addCriterion("log_path like", value, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathNotLike(String value) {
            addCriterion("log_path not like", value, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathIn(List<String> values) {
            addCriterion("log_path in", values, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathNotIn(List<String> values) {
            addCriterion("log_path not in", values, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathBetween(String value1, String value2) {
            addCriterion("log_path between", value1, value2, "logPath");
            return (Criteria) this;
        }

        public Criteria andLogPathNotBetween(String value1, String value2) {
            addCriterion("log_path not between", value1, value2, "logPath");
            return (Criteria) this;
        }

        public Criteria andCycleIsNull() {
            addCriterion("cycle is null");
            return (Criteria) this;
        }

        public Criteria andCycleIsNotNull() {
            addCriterion("cycle is not null");
            return (Criteria) this;
        }

        public Criteria andCycleEqualTo(String value) {
            addCriterion("cycle =", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotEqualTo(String value) {
            addCriterion("cycle <>", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThan(String value) {
            addCriterion("cycle >", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThanOrEqualTo(String value) {
            addCriterion("cycle >=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThan(String value) {
            addCriterion("cycle <", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThanOrEqualTo(String value) {
            addCriterion("cycle <=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLike(String value) {
            addCriterion("cycle like", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotLike(String value) {
            addCriterion("cycle not like", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleIn(List<String> values) {
            addCriterion("cycle in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotIn(List<String> values) {
            addCriterion("cycle not in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleBetween(String value1, String value2) {
            addCriterion("cycle between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotBetween(String value1, String value2) {
            addCriterion("cycle not between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andTimeIdIsNull() {
            addCriterion("time_id is null");
            return (Criteria) this;
        }

        public Criteria andTimeIdIsNotNull() {
            addCriterion("time_id is not null");
            return (Criteria) this;
        }

        public Criteria andTimeIdEqualTo(String value) {
            addCriterion("time_id =", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotEqualTo(String value) {
            addCriterion("time_id <>", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThan(String value) {
            addCriterion("time_id >", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThanOrEqualTo(String value) {
            addCriterion("time_id >=", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThan(String value) {
            addCriterion("time_id <", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThanOrEqualTo(String value) {
            addCriterion("time_id <=", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdLike(String value) {
            addCriterion("time_id like", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotLike(String value) {
            addCriterion("time_id not like", value, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdIn(List<String> values) {
            addCriterion("time_id in", values, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotIn(List<String> values) {
            addCriterion("time_id not in", values, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdBetween(String value1, String value2) {
            addCriterion("time_id between", value1, value2, "timeId");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotBetween(String value1, String value2) {
            addCriterion("time_id not between", value1, value2, "timeId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIfWaitIsNull() {
            addCriterion("if_wait is null");
            return (Criteria) this;
        }

        public Criteria andIfWaitIsNotNull() {
            addCriterion("if_wait is not null");
            return (Criteria) this;
        }

        public Criteria andIfWaitEqualTo(Integer value) {
            addCriterion("if_wait =", value, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitNotEqualTo(Integer value) {
            addCriterion("if_wait <>", value, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitGreaterThan(Integer value) {
            addCriterion("if_wait >", value, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitGreaterThanOrEqualTo(Integer value) {
            addCriterion("if_wait >=", value, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitLessThan(Integer value) {
            addCriterion("if_wait <", value, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitLessThanOrEqualTo(Integer value) {
            addCriterion("if_wait <=", value, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitIn(List<Integer> values) {
            addCriterion("if_wait in", values, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitNotIn(List<Integer> values) {
            addCriterion("if_wait not in", values, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitBetween(Integer value1, Integer value2) {
            addCriterion("if_wait between", value1, value2, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfWaitNotBetween(Integer value1, Integer value2) {
            addCriterion("if_wait not between", value1, value2, "ifWait");
            return (Criteria) this;
        }

        public Criteria andIfRecallIsNull() {
            addCriterion("if_recall is null");
            return (Criteria) this;
        }

        public Criteria andIfRecallIsNotNull() {
            addCriterion("if_recall is not null");
            return (Criteria) this;
        }

        public Criteria andIfRecallEqualTo(Integer value) {
            addCriterion("if_recall =", value, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallNotEqualTo(Integer value) {
            addCriterion("if_recall <>", value, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallGreaterThan(Integer value) {
            addCriterion("if_recall >", value, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallGreaterThanOrEqualTo(Integer value) {
            addCriterion("if_recall >=", value, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallLessThan(Integer value) {
            addCriterion("if_recall <", value, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallLessThanOrEqualTo(Integer value) {
            addCriterion("if_recall <=", value, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallIn(List<Integer> values) {
            addCriterion("if_recall in", values, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallNotIn(List<Integer> values) {
            addCriterion("if_recall not in", values, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallBetween(Integer value1, Integer value2) {
            addCriterion("if_recall between", value1, value2, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfRecallNotBetween(Integer value1, Integer value2) {
            addCriterion("if_recall not between", value1, value2, "ifRecall");
            return (Criteria) this;
        }

        public Criteria andIfPreIsNull() {
            addCriterion("if_pre is null");
            return (Criteria) this;
        }

        public Criteria andIfPreIsNotNull() {
            addCriterion("if_pre is not null");
            return (Criteria) this;
        }

        public Criteria andIfPreEqualTo(Integer value) {
            addCriterion("if_pre =", value, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreNotEqualTo(Integer value) {
            addCriterion("if_pre <>", value, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreGreaterThan(Integer value) {
            addCriterion("if_pre >", value, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreGreaterThanOrEqualTo(Integer value) {
            addCriterion("if_pre >=", value, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreLessThan(Integer value) {
            addCriterion("if_pre <", value, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreLessThanOrEqualTo(Integer value) {
            addCriterion("if_pre <=", value, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreIn(List<Integer> values) {
            addCriterion("if_pre in", values, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreNotIn(List<Integer> values) {
            addCriterion("if_pre not in", values, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreBetween(Integer value1, Integer value2) {
            addCriterion("if_pre between", value1, value2, "ifPre");
            return (Criteria) this;
        }

        public Criteria andIfPreNotBetween(Integer value1, Integer value2) {
            addCriterion("if_pre not between", value1, value2, "ifPre");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andRecallNumIsNull() {
            addCriterion("recall_num is null");
            return (Criteria) this;
        }

        public Criteria andRecallNumIsNotNull() {
            addCriterion("recall_num is not null");
            return (Criteria) this;
        }

        public Criteria andRecallNumEqualTo(Integer value) {
            addCriterion("recall_num =", value, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumNotEqualTo(Integer value) {
            addCriterion("recall_num <>", value, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumGreaterThan(Integer value) {
            addCriterion("recall_num >", value, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("recall_num >=", value, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumLessThan(Integer value) {
            addCriterion("recall_num <", value, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumLessThanOrEqualTo(Integer value) {
            addCriterion("recall_num <=", value, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumIn(List<Integer> values) {
            addCriterion("recall_num in", values, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumNotIn(List<Integer> values) {
            addCriterion("recall_num not in", values, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumBetween(Integer value1, Integer value2) {
            addCriterion("recall_num between", value1, value2, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRecallNumNotBetween(Integer value1, Integer value2) {
            addCriterion("recall_num not between", value1, value2, "recallNum");
            return (Criteria) this;
        }

        public Criteria andRunNumIsNull() {
            addCriterion("run_num is null");
            return (Criteria) this;
        }

        public Criteria andRunNumIsNotNull() {
            addCriterion("run_num is not null");
            return (Criteria) this;
        }

        public Criteria andRunNumEqualTo(Integer value) {
            addCriterion("run_num =", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumNotEqualTo(Integer value) {
            addCriterion("run_num <>", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumGreaterThan(Integer value) {
            addCriterion("run_num >", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("run_num >=", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumLessThan(Integer value) {
            addCriterion("run_num <", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumLessThanOrEqualTo(Integer value) {
            addCriterion("run_num <=", value, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumIn(List<Integer> values) {
            addCriterion("run_num in", values, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumNotIn(List<Integer> values) {
            addCriterion("run_num not in", values, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumBetween(Integer value1, Integer value2) {
            addCriterion("run_num between", value1, value2, "runNum");
            return (Criteria) this;
        }

        public Criteria andRunNumNotBetween(Integer value1, Integer value2) {
            addCriterion("run_num not between", value1, value2, "runNum");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalIsNull() {
            addCriterion("recall_interval is null");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalIsNotNull() {
            addCriterion("recall_interval is not null");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalEqualTo(Integer value) {
            addCriterion("recall_interval =", value, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalNotEqualTo(Integer value) {
            addCriterion("recall_interval <>", value, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalGreaterThan(Integer value) {
            addCriterion("recall_interval >", value, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalGreaterThanOrEqualTo(Integer value) {
            addCriterion("recall_interval >=", value, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalLessThan(Integer value) {
            addCriterion("recall_interval <", value, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalLessThanOrEqualTo(Integer value) {
            addCriterion("recall_interval <=", value, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalIn(List<Integer> values) {
            addCriterion("recall_interval in", values, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalNotIn(List<Integer> values) {
            addCriterion("recall_interval not in", values, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalBetween(Integer value1, Integer value2) {
            addCriterion("recall_interval between", value1, value2, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallIntervalNotBetween(Integer value1, Integer value2) {
            addCriterion("recall_interval not between", value1, value2, "recallInterval");
            return (Criteria) this;
        }

        public Criteria andRecallLimitIsNull() {
            addCriterion("recall_limit is null");
            return (Criteria) this;
        }

        public Criteria andRecallLimitIsNotNull() {
            addCriterion("recall_limit is not null");
            return (Criteria) this;
        }

        public Criteria andRecallLimitEqualTo(Integer value) {
            addCriterion("recall_limit =", value, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitNotEqualTo(Integer value) {
            addCriterion("recall_limit <>", value, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitGreaterThan(Integer value) {
            addCriterion("recall_limit >", value, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("recall_limit >=", value, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitLessThan(Integer value) {
            addCriterion("recall_limit <", value, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitLessThanOrEqualTo(Integer value) {
            addCriterion("recall_limit <=", value, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitIn(List<Integer> values) {
            addCriterion("recall_limit in", values, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitNotIn(List<Integer> values) {
            addCriterion("recall_limit not in", values, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitBetween(Integer value1, Integer value2) {
            addCriterion("recall_limit between", value1, value2, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andRecallLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("recall_limit not between", value1, value2, "recallLimit");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andTimeStampIsNull() {
            addCriterion("time_stamp is null");
            return (Criteria) this;
        }

        public Criteria andTimeStampIsNotNull() {
            addCriterion("time_stamp is not null");
            return (Criteria) this;
        }

        public Criteria andTimeStampEqualTo(Date value) {
            addCriterion("time_stamp =", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampNotEqualTo(Date value) {
            addCriterion("time_stamp <>", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampGreaterThan(Date value) {
            addCriterion("time_stamp >", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampGreaterThanOrEqualTo(Date value) {
            addCriterion("time_stamp >=", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampLessThan(Date value) {
            addCriterion("time_stamp <", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampLessThanOrEqualTo(Date value) {
            addCriterion("time_stamp <=", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampIn(List<Date> values) {
            addCriterion("time_stamp in", values, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampNotIn(List<Date> values) {
            addCriterion("time_stamp not in", values, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampBetween(Date value1, Date value2) {
            addCriterion("time_stamp between", value1, value2, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampNotBetween(Date value1, Date value2) {
            addCriterion("time_stamp not between", value1, value2, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andCalDtIsNull() {
            addCriterion("cal_dt is null");
            return (Criteria) this;
        }

        public Criteria andCalDtIsNotNull() {
            addCriterion("cal_dt is not null");
            return (Criteria) this;
        }

        public Criteria andCalDtEqualTo(String value) {
            addCriterion("cal_dt =", value, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtNotEqualTo(String value) {
            addCriterion("cal_dt <>", value, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtGreaterThan(String value) {
            addCriterion("cal_dt >", value, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtGreaterThanOrEqualTo(String value) {
            addCriterion("cal_dt >=", value, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtLessThan(String value) {
            addCriterion("cal_dt <", value, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtLessThanOrEqualTo(String value) {
            addCriterion("cal_dt <=", value, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtLike(String value) {
            addCriterion("cal_dt like", value, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtNotLike(String value) {
            addCriterion("cal_dt not like", value, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtIn(List<String> values) {
            addCriterion("cal_dt in", values, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtNotIn(List<String> values) {
            addCriterion("cal_dt not in", values, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtBetween(String value1, String value2) {
            addCriterion("cal_dt between", value1, value2, "calDt");
            return (Criteria) this;
        }

        public Criteria andCalDtNotBetween(String value1, String value2) {
            addCriterion("cal_dt not between", value1, value2, "calDt");
            return (Criteria) this;
        }

        public Criteria andFreqIsNull() {
            addCriterion("freq is null");
            return (Criteria) this;
        }

        public Criteria andFreqIsNotNull() {
            addCriterion("freq is not null");
            return (Criteria) this;
        }

        public Criteria andFreqEqualTo(String value) {
            addCriterion("freq =", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqNotEqualTo(String value) {
            addCriterion("freq <>", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqGreaterThan(String value) {
            addCriterion("freq >", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqGreaterThanOrEqualTo(String value) {
            addCriterion("freq >=", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqLessThan(String value) {
            addCriterion("freq <", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqLessThanOrEqualTo(String value) {
            addCriterion("freq <=", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqLike(String value) {
            addCriterion("freq like", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqNotLike(String value) {
            addCriterion("freq not like", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqIn(List<String> values) {
            addCriterion("freq in", values, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqNotIn(List<String> values) {
            addCriterion("freq not in", values, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqBetween(String value1, String value2) {
            addCriterion("freq between", value1, value2, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqNotBetween(String value1, String value2) {
            addCriterion("freq not between", value1, value2, "freq");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("owner is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("owner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("owner =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("owner <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("owner >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("owner >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("owner <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("owner <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("owner like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("owner not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("owner in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("owner not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("owner between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("owner not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIsNull() {
            addCriterion("trigger_time is null");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIsNotNull() {
            addCriterion("trigger_time is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeEqualTo(Long value) {
            addCriterion("trigger_time =", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotEqualTo(Long value) {
            addCriterion("trigger_time <>", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeGreaterThan(Long value) {
            addCriterion("trigger_time >", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("trigger_time >=", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeLessThan(Long value) {
            addCriterion("trigger_time <", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeLessThanOrEqualTo(Long value) {
            addCriterion("trigger_time <=", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIn(List<Long> values) {
            addCriterion("trigger_time in", values, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotIn(List<Long> values) {
            addCriterion("trigger_time not in", values, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeBetween(Long value1, Long value2) {
            addCriterion("trigger_time between", value1, value2, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotBetween(Long value1, Long value2) {
            addCriterion("trigger_time not between", value1, value2, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andWaitCodeIsNull() {
            addCriterion("wait_code is null");
            return (Criteria) this;
        }

        public Criteria andWaitCodeIsNotNull() {
            addCriterion("wait_code is not null");
            return (Criteria) this;
        }

        public Criteria andWaitCodeEqualTo(String value) {
            addCriterion("wait_code =", value, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeNotEqualTo(String value) {
            addCriterion("wait_code <>", value, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeGreaterThan(String value) {
            addCriterion("wait_code >", value, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeGreaterThanOrEqualTo(String value) {
            addCriterion("wait_code >=", value, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeLessThan(String value) {
            addCriterion("wait_code <", value, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeLessThanOrEqualTo(String value) {
            addCriterion("wait_code <=", value, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeLike(String value) {
            addCriterion("wait_code like", value, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeNotLike(String value) {
            addCriterion("wait_code not like", value, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeIn(List<String> values) {
            addCriterion("wait_code in", values, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeNotIn(List<String> values) {
            addCriterion("wait_code not in", values, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeBetween(String value1, String value2) {
            addCriterion("wait_code between", value1, value2, "waitCode");
            return (Criteria) this;
        }

        public Criteria andWaitCodeNotBetween(String value1, String value2) {
            addCriterion("wait_code not between", value1, value2, "waitCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeIsNull() {
            addCriterion("recall_code is null");
            return (Criteria) this;
        }

        public Criteria andRecallCodeIsNotNull() {
            addCriterion("recall_code is not null");
            return (Criteria) this;
        }

        public Criteria andRecallCodeEqualTo(String value) {
            addCriterion("recall_code =", value, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeNotEqualTo(String value) {
            addCriterion("recall_code <>", value, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeGreaterThan(String value) {
            addCriterion("recall_code >", value, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeGreaterThanOrEqualTo(String value) {
            addCriterion("recall_code >=", value, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeLessThan(String value) {
            addCriterion("recall_code <", value, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeLessThanOrEqualTo(String value) {
            addCriterion("recall_code <=", value, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeLike(String value) {
            addCriterion("recall_code like", value, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeNotLike(String value) {
            addCriterion("recall_code not like", value, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeIn(List<String> values) {
            addCriterion("recall_code in", values, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeNotIn(List<String> values) {
            addCriterion("recall_code not in", values, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeBetween(String value1, String value2) {
            addCriterion("recall_code between", value1, value2, "recallCode");
            return (Criteria) this;
        }

        public Criteria andRecallCodeNotBetween(String value1, String value2) {
            addCriterion("recall_code not between", value1, value2, "recallCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeIsNull() {
            addCriterion("success_code is null");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeIsNotNull() {
            addCriterion("success_code is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeEqualTo(String value) {
            addCriterion("success_code =", value, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeNotEqualTo(String value) {
            addCriterion("success_code <>", value, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeGreaterThan(String value) {
            addCriterion("success_code >", value, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("success_code >=", value, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeLessThan(String value) {
            addCriterion("success_code <", value, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeLessThanOrEqualTo(String value) {
            addCriterion("success_code <=", value, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeLike(String value) {
            addCriterion("success_code like", value, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeNotLike(String value) {
            addCriterion("success_code not like", value, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeIn(List<String> values) {
            addCriterion("success_code in", values, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeNotIn(List<String> values) {
            addCriterion("success_code not in", values, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeBetween(String value1, String value2) {
            addCriterion("success_code between", value1, value2, "successCode");
            return (Criteria) this;
        }

        public Criteria andSuccessCodeNotBetween(String value1, String value2) {
            addCriterion("success_code not between", value1, value2, "successCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeIsNull() {
            addCriterion("job_code is null");
            return (Criteria) this;
        }

        public Criteria andJobCodeIsNotNull() {
            addCriterion("job_code is not null");
            return (Criteria) this;
        }

        public Criteria andJobCodeEqualTo(Integer value) {
            addCriterion("job_code =", value, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeNotEqualTo(Integer value) {
            addCriterion("job_code <>", value, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeGreaterThan(Integer value) {
            addCriterion("job_code >", value, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("job_code >=", value, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeLessThan(Integer value) {
            addCriterion("job_code <", value, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeLessThanOrEqualTo(Integer value) {
            addCriterion("job_code <=", value, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeIn(List<Integer> values) {
            addCriterion("job_code in", values, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeNotIn(List<Integer> values) {
            addCriterion("job_code not in", values, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeBetween(Integer value1, Integer value2) {
            addCriterion("job_code between", value1, value2, "jobCode");
            return (Criteria) this;
        }

        public Criteria andJobCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("job_code not between", value1, value2, "jobCode");
            return (Criteria) this;
        }

        public Criteria andRunningPrioIsNull() {
            addCriterion("running_prio is null");
            return (Criteria) this;
        }

        public Criteria andRunningPrioIsNotNull() {
            addCriterion("running_prio is not null");
            return (Criteria) this;
        }

        public Criteria andRunningPrioEqualTo(Integer value) {
            addCriterion("running_prio =", value, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioNotEqualTo(Integer value) {
            addCriterion("running_prio <>", value, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioGreaterThan(Integer value) {
            addCriterion("running_prio >", value, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioGreaterThanOrEqualTo(Integer value) {
            addCriterion("running_prio >=", value, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioLessThan(Integer value) {
            addCriterion("running_prio <", value, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioLessThanOrEqualTo(Integer value) {
            addCriterion("running_prio <=", value, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioIn(List<Integer> values) {
            addCriterion("running_prio in", values, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioNotIn(List<Integer> values) {
            addCriterion("running_prio not in", values, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioBetween(Integer value1, Integer value2) {
            addCriterion("running_prio between", value1, value2, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andRunningPrioNotBetween(Integer value1, Integer value2) {
            addCriterion("running_prio not between", value1, value2, "runningPrio");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNull() {
            addCriterion("timeout is null");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNotNull() {
            addCriterion("timeout is not null");
            return (Criteria) this;
        }

        public Criteria andTimeoutEqualTo(Integer value) {
            addCriterion("timeout =", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotEqualTo(Integer value) {
            addCriterion("timeout <>", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThan(Integer value) {
            addCriterion("timeout >", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("timeout >=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThan(Integer value) {
            addCriterion("timeout <", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("timeout <=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutIn(List<Integer> values) {
            addCriterion("timeout in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotIn(List<Integer> values) {
            addCriterion("timeout not in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("timeout between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("timeout not between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgIsNull() {
            addCriterion("job_codemsg is null");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgIsNotNull() {
            addCriterion("job_codemsg is not null");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgEqualTo(String value) {
            addCriterion("job_codemsg =", value, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgNotEqualTo(String value) {
            addCriterion("job_codemsg <>", value, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgGreaterThan(String value) {
            addCriterion("job_codemsg >", value, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgGreaterThanOrEqualTo(String value) {
            addCriterion("job_codemsg >=", value, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgLessThan(String value) {
            addCriterion("job_codemsg <", value, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgLessThanOrEqualTo(String value) {
            addCriterion("job_codemsg <=", value, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgLike(String value) {
            addCriterion("job_codemsg like", value, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgNotLike(String value) {
            addCriterion("job_codemsg not like", value, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgIn(List<String> values) {
            addCriterion("job_codemsg in", values, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgNotIn(List<String> values) {
            addCriterion("job_codemsg not in", values, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgBetween(String value1, String value2) {
            addCriterion("job_codemsg between", value1, value2, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andJobCodemsgNotBetween(String value1, String value2) {
            addCriterion("job_codemsg not between", value1, value2, "jobCodemsg");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterIsNull() {
            addCriterion("task_committer is null");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterIsNotNull() {
            addCriterion("task_committer is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterEqualTo(String value) {
            addCriterion("task_committer =", value, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterNotEqualTo(String value) {
            addCriterion("task_committer <>", value, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterGreaterThan(String value) {
            addCriterion("task_committer >", value, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterGreaterThanOrEqualTo(String value) {
            addCriterion("task_committer >=", value, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterLessThan(String value) {
            addCriterion("task_committer <", value, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterLessThanOrEqualTo(String value) {
            addCriterion("task_committer <=", value, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterLike(String value) {
            addCriterion("task_committer like", value, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterNotLike(String value) {
            addCriterion("task_committer not like", value, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterIn(List<String> values) {
            addCriterion("task_committer in", values, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterNotIn(List<String> values) {
            addCriterion("task_committer not in", values, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterBetween(String value1, String value2) {
            addCriterion("task_committer between", value1, value2, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andTaskCommitterNotBetween(String value1, String value2) {
            addCriterion("task_committer not between", value1, value2, "taskCommitter");
            return (Criteria) this;
        }

        public Criteria andConcurrencyIsNull() {
            addCriterion("concurrency is null");
            return (Criteria) this;
        }

        public Criteria andConcurrencyIsNotNull() {
            addCriterion("concurrency is not null");
            return (Criteria) this;
        }

        public Criteria andConcurrencyEqualTo(Integer value) {
            addCriterion("concurrency =", value, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyNotEqualTo(Integer value) {
            addCriterion("concurrency <>", value, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyGreaterThan(Integer value) {
            addCriterion("concurrency >", value, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyGreaterThanOrEqualTo(Integer value) {
            addCriterion("concurrency >=", value, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLessThan(Integer value) {
            addCriterion("concurrency <", value, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLessThanOrEqualTo(Integer value) {
            addCriterion("concurrency <=", value, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyIn(List<Integer> values) {
            addCriterion("concurrency in", values, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyNotIn(List<Integer> values) {
            addCriterion("concurrency not in", values, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyBetween(Integer value1, Integer value2) {
            addCriterion("concurrency between", value1, value2, "concurrency");
            return (Criteria) this;
        }

        public Criteria andConcurrencyNotBetween(Integer value1, Integer value2) {
            addCriterion("concurrency not between", value1, value2, "concurrency");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}