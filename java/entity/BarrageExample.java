package entity;

import java.util.ArrayList;
import java.util.List;

public class BarrageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BarrageExample() {
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

        public Criteria andBarrageidIsNull() {
            addCriterion("BARRAGEID is null");
            return (Criteria) this;
        }

        public Criteria andBarrageidIsNotNull() {
            addCriterion("BARRAGEID is not null");
            return (Criteria) this;
        }

        public Criteria andBarrageidEqualTo(Integer value) {
            addCriterion("BARRAGEID =", value, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidNotEqualTo(Integer value) {
            addCriterion("BARRAGEID <>", value, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidGreaterThan(Integer value) {
            addCriterion("BARRAGEID >", value, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidGreaterThanOrEqualTo(Integer value) {
            addCriterion("BARRAGEID >=", value, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidLessThan(Integer value) {
            addCriterion("BARRAGEID <", value, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidLessThanOrEqualTo(Integer value) {
            addCriterion("BARRAGEID <=", value, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidIn(List<Integer> values) {
            addCriterion("BARRAGEID in", values, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidNotIn(List<Integer> values) {
            addCriterion("BARRAGEID not in", values, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidBetween(Integer value1, Integer value2) {
            addCriterion("BARRAGEID between", value1, value2, "barrageid");
            return (Criteria) this;
        }

        public Criteria andBarrageidNotBetween(Integer value1, Integer value2) {
            addCriterion("BARRAGEID not between", value1, value2, "barrageid");
            return (Criteria) this;
        }

        public Criteria andVideoidIsNull() {
            addCriterion("VIDEOID is null");
            return (Criteria) this;
        }

        public Criteria andVideoidIsNotNull() {
            addCriterion("VIDEOID is not null");
            return (Criteria) this;
        }

        public Criteria andVideoidEqualTo(Integer value) {
            addCriterion("VIDEOID =", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidNotEqualTo(Integer value) {
            addCriterion("VIDEOID <>", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidGreaterThan(Integer value) {
            addCriterion("VIDEOID >", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidGreaterThanOrEqualTo(Integer value) {
            addCriterion("VIDEOID >=", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidLessThan(Integer value) {
            addCriterion("VIDEOID <", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidLessThanOrEqualTo(Integer value) {
            addCriterion("VIDEOID <=", value, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidIn(List<Integer> values) {
            addCriterion("VIDEOID in", values, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidNotIn(List<Integer> values) {
            addCriterion("VIDEOID not in", values, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidBetween(Integer value1, Integer value2) {
            addCriterion("VIDEOID between", value1, value2, "videoid");
            return (Criteria) this;
        }

        public Criteria andVideoidNotBetween(Integer value1, Integer value2) {
            addCriterion("VIDEOID not between", value1, value2, "videoid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("USERID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("USERID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("USERID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("USERID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("USERID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("USERID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("USERID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("USERID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("USERID like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("USERID not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("USERID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("USERID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("USERID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("USERID not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andVideotimeIsNull() {
            addCriterion("VIDEOTIME is null");
            return (Criteria) this;
        }

        public Criteria andVideotimeIsNotNull() {
            addCriterion("VIDEOTIME is not null");
            return (Criteria) this;
        }

        public Criteria andVideotimeEqualTo(String value) {
            addCriterion("VIDEOTIME =", value, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeNotEqualTo(String value) {
            addCriterion("VIDEOTIME <>", value, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeGreaterThan(String value) {
            addCriterion("VIDEOTIME >", value, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeGreaterThanOrEqualTo(String value) {
            addCriterion("VIDEOTIME >=", value, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeLessThan(String value) {
            addCriterion("VIDEOTIME <", value, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeLessThanOrEqualTo(String value) {
            addCriterion("VIDEOTIME <=", value, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeLike(String value) {
            addCriterion("VIDEOTIME like", value, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeNotLike(String value) {
            addCriterion("VIDEOTIME not like", value, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeIn(List<String> values) {
            addCriterion("VIDEOTIME in", values, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeNotIn(List<String> values) {
            addCriterion("VIDEOTIME not in", values, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeBetween(String value1, String value2) {
            addCriterion("VIDEOTIME between", value1, value2, "videotime");
            return (Criteria) this;
        }

        public Criteria andVideotimeNotBetween(String value1, String value2) {
            addCriterion("VIDEOTIME not between", value1, value2, "videotime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeIsNull() {
            addCriterion("BARRAGETIME is null");
            return (Criteria) this;
        }

        public Criteria andBarragetimeIsNotNull() {
            addCriterion("BARRAGETIME is not null");
            return (Criteria) this;
        }

        public Criteria andBarragetimeEqualTo(String value) {
            addCriterion("BARRAGETIME =", value, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeNotEqualTo(String value) {
            addCriterion("BARRAGETIME <>", value, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeGreaterThan(String value) {
            addCriterion("BARRAGETIME >", value, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeGreaterThanOrEqualTo(String value) {
            addCriterion("BARRAGETIME >=", value, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeLessThan(String value) {
            addCriterion("BARRAGETIME <", value, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeLessThanOrEqualTo(String value) {
            addCriterion("BARRAGETIME <=", value, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeLike(String value) {
            addCriterion("BARRAGETIME like", value, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeNotLike(String value) {
            addCriterion("BARRAGETIME not like", value, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeIn(List<String> values) {
            addCriterion("BARRAGETIME in", values, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeNotIn(List<String> values) {
            addCriterion("BARRAGETIME not in", values, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeBetween(String value1, String value2) {
            addCriterion("BARRAGETIME between", value1, value2, "barragetime");
            return (Criteria) this;
        }

        public Criteria andBarragetimeNotBetween(String value1, String value2) {
            addCriterion("BARRAGETIME not between", value1, value2, "barragetime");
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