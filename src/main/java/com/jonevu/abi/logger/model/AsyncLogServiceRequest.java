/**
 * 
 */
package com.jonevu.abi.logger.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jonev
 *
 */
@Transactional
@Entity
@Table(name = "ABI_SERV_REQ_LOG")
public class AsyncLogServiceRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7903204541196980078L;
	
	@Id
    @Column(name = "REQ_ID")
    private Long reqId;

	@JsonProperty("OrderDate")
    @Column(name = "SVC_NAME")
    private String svcName;

    @Column(name = "REQ_DATE")
    private Date reqDate;

    @Column(name = "SYS_ID")
    private String sysId;

    @Lob
    @Column(name = "REQ_MSG")
    private String reqMsg;

    @Column(name = "MSG_TYPE")
    private String msgType;

    @Column(name = "UPDATED_DATE")
    private Timestamp lastUpdatedDate;

    @Column(name = "UPDATED_BY")
    private String lastUpdatedBy;

	/**
	 * @return the reqId
	 */
	public Long getReqId() {
		return reqId;
	}

	/**
	 * @param reqId the reqId to set
	 */
	public void setReqId(Long reqId) {
		this.reqId = reqId;
	}

	/**
	 * @return the svcName
	 */
	public String getSvcName() {
		return svcName;
	}

	/**
	 * @param svcName the svcName to set
	 */
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}

	/**
	 * @return the reqDate
	 */
	public Date getReqDate() {
		return reqDate;
	}

	/**
	 * @param reqDate the reqDate to set
	 */
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	/**
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	/**
	 * @return the reqMsg
	 */
	public String getReqMsg() {
		return reqMsg;
	}

	/**
	 * @param reqMsg the reqMsg to set
	 */
	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}

	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @return the lastUpdatedBy
	 */
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * @param lastUpdatedBy the lastUpdatedBy to set
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}    
}
