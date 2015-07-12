package com.fanli.scheduler.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EtlTaskCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EtlTaskCfgExample() {
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

        public Criteria andIfEnableIsNull() {
            addCriterion("if_enable is null");
            return (Criteria) this;
        }

        public Criteria andIfEnableIsNotNull() {
            addCriterion("if_enable is not null");
            return (Criteria) this;
        }

        public Criteria andIfEnableEqualTo(Integer value) {
            addCriterion("if_enable =", value, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableNotEqualTo(Integer value) {
            addCriterion("if_enable <>", value, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableGreaterThan(Integer value) {
            addCriterion("if_enable >", value, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("if_enable >=", value, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableLessThan(Integer value) {
            addCriterion("if_enable <", value, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableLessThanOrEqualTo(Integer value) {
            addCriterion("if_enable <=", value, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableIn(List<Integer> values) {
            addCriterion("if_enable in", values, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableNotIn(List<Integer> values) {
            addCriterion("if_enable not in", values, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableBetween(Integer value1, Integer value2) {
            addCriterion("if_enable between", value1, value2, "ifEnable");
            return (Criteria) this;
        }

        public Criteria andIfEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("if_enable not between", value1, value2, "ifEnable");
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

        public Criteria andLogFileIsNull() {
            addCriterion("log_file is null");
            return (Criteria) this;
        }

        public Criteria andLogFileIsNotNull() {
            addCriterion("log_file is not null");
            return (Criteria) this;
        }

        public Criteria andLogFileEqualTo(String value) {
            addCriterion("log_file =", value, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileNotEqualTo(String value) {
            addCriterion("log_file <>", value, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileGreaterThan(String value) {
            addCriterion("log_file >", value, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileGreaterThanOrEqualTo(String value) {
            addCriterion("log_file >=", value, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileLessThan(String value) {
            addCriterion("log_file <", value, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileLessThanOrEqualTo(String value) {
            addCriterion("log_file <=", value, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileLike(String value) {
            addCriterion("log_file like", value, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileNotLike(String value) {
            addCriterion("log_file not like", value, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileIn(List<String> values) {
            addCriterion("log_file in", values, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileNotIn(List<String> values) {
            addCriterion("log_file not in", values, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileBetween(String value1, String value2) {
            addCriterion("log_file between", value1, value2, "logFile");
            return (Criteria) this;
        }

        public Criteria andLogFileNotBetween(String value1, String value2) {
            addCriterion("log_file not between", value1, value2, "logFile");
            return (Criteria) this;
        }

        public Criteria andAddUserIsNull() {
            addCriterion("add_user is null");
            return (Criteria) this;
        }

        public Criteria andAddUserIsNotNull() {
            addCriterion("add_user is not null");
            return (Criteria) this;
        }

        public Criteria andAddUserEqualTo(String value) {
            addCriterion("add_user =", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserNotEqualTo(String value) {
            addCriterion("add_user <>", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserGreaterThan(String value) {
            addCriterion("add_user >", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserGreaterThanOrEqualTo(String value) {
            addCriterion("add_user >=", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserLessThan(String value) {
            addCriterion("add_user <", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserLessThanOrEqualTo(String value) {
            addCriterion("add_user <=", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserLike(String value) {
            addCriterion("add_user like", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserNotLike(String value) {
            addCriterion("add_user not like", value, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserIn(List<String> values) {
            addCriterion("add_user in", values, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserNotIn(List<String> values) {
            addCriterion("add_user not in", values, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserBetween(String value1, String value2) {
            addCriterion("add_user between", value1, value2, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddUserNotBetween(String value1, String value2) {
            addCriterion("add_user not between", value1, value2, "addUser");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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

        public Criteria andOffsetIsNull() {
            addCriterion("offset is null");
            return (Criteria) this;
        }

        public Criteria andOffsetIsNotNull() {
            addCriterion("offset is not null");
            return (Criteria) this;
        }

        public Criteria andOffsetEqualTo(String value) {
            addCriterion("offset =", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotEqualTo(String value) {
            addCriterion("offset <>", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetGreaterThan(String value) {
            addCriterion("offset >", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetGreaterThanOrEqualTo(String value) {
            addCriterion("offset >=", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetLessThan(String value) {
            addCriterion("offset <", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetLessThanOrEqualTo(String value) {
            addCriterion("offset <=", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetLike(String value) {
            addCriterion("offset like", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotLike(String value) {
            addCriterion("offset not like", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetIn(List<String> values) {
            addCriterion("offset in", values, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotIn(List<String> values) {
            addCriterion("offset not in", values, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetBetween(String value1, String value2) {
            addCriterion("offset between", value1, value2, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotBetween(String value1, String value2) {
            addCriterion("offset not between", value1, value2, "offset");
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