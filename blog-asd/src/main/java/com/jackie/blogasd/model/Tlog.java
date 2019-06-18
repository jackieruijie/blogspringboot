package com.jackie.blogasd.model;

import java.util.Date;

public class Tlog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log.log_id
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    private String logId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log.op_type
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    private Integer opType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log.create_time
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log.content
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log.tu_id
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    private String tuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log.op_ip
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    private String opIp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log.log_id
     *
     * @return the value of t_log.log_id
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public String getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log.log_id
     *
     * @param logId the value for t_log.log_id
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log.op_type
     *
     * @return the value of t_log.op_type
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log.op_type
     *
     * @param opType the value for t_log.op_type
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log.create_time
     *
     * @return the value of t_log.create_time
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log.create_time
     *
     * @param createTime the value for t_log.create_time
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log.content
     *
     * @return the value of t_log.content
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log.content
     *
     * @param content the value for t_log.content
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log.tu_id
     *
     * @return the value of t_log.tu_id
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public String getTuId() {
        return tuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log.tu_id
     *
     * @param tuId the value for t_log.tu_id
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public void setTuId(String tuId) {
        this.tuId = tuId == null ? null : tuId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log.op_ip
     *
     * @return the value of t_log.op_ip
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public String getOpIp() {
        return opIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log.op_ip
     *
     * @param opIp the value for t_log.op_ip
     *
     * @mbg.generated Tue Jun 18 15:39:52 CST 2019
     */
    public void setOpIp(String opIp) {
        this.opIp = opIp == null ? null : opIp.trim();
    }
}