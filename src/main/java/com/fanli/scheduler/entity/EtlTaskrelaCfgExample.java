package com.fanli.scheduler.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EtlTaskrelaCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EtlTaskrelaCfgExample() {
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

        public Criteria andPreIdIsNull() {
            addCriterion("pre_id is null");
            return (Criteria) this;
        }

        public Criteria andPreIdIsNotNull() {
            addCriterion("pre_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreIdEqualTo(Integer value) {
            addCriterion("pre_id =", value, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdNotEqualTo(Integer value) {
            addCriterion("pre_id <>", value, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdGreaterThan(Integer value) {
            addCriterion("pre_id >", value, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pre_id >=", value, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdLessThan(Integer value) {
            addCriterion("pre_id <", value, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdLessThanOrEqualTo(Integer value) {
            addCriterion("pre_id <=", value, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdIn(List<Integer> values) {
            addCriterion("pre_id in", values, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdNotIn(List<Integer> values) {
            addCriterion("pre_id not in", values, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdBetween(Integer value1, Integer value2) {
            addCriterion("pre_id between", value1, value2, "preId");
            return (Criteria) this;
        }

        public Criteria andPreIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pre_id not between", value1, value2, "preId");
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

        public Criteria andOffsetEqualTo(Integer value) {
            addCriterion("offset =", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotEqualTo(Integer value) {
            addCriterion("offset <>", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetGreaterThan(Integer value) {
            addCriterion("offset >", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetGreaterThanOrEqualTo(Integer value) {
            addCriterion("offset >=", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetLessThan(Integer value) {
            addCriterion("offset <", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetLessThanOrEqualTo(Integer value) {
            addCriterion("offset <=", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetIn(List<Integer> values) {
            addCriterion("offset in", values, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotIn(List<Integer> values) {
            addCriterion("offset not in", values, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetBetween(Integer value1, Integer value2) {
            addCriterion("offset between", value1, value2, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotBetween(Integer value1, Integer value2) {
            addCriterion("offset not between", value1, value2, "offset");
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

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
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