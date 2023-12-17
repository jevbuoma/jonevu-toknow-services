/**
 * 
 */
package com.jonevu.abi.logger.model;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author jonev
 *
 */
@Transactional
@Entity
@Table(name="ABI_SERV_RES_LOG")
@SequenceGenerator(name = "LOG_SERV_SEQ", sequenceName = "LOG_SERV_SEQUENCE")
public class AsyncLogServiceResponse {
	
	@Id
    @Column(name = "RES_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,  generator = "SeqGen")
	@SequenceGenerator(name = "SeqGen", sequenceName = "LOG_SERV_SEQUENCE", allocationSize = 1)
    private Long resId;
	
    @Column(name = "REQ_ID")
    private Long reqId;
	
    @Column(name = "SVC_NAME")
    private String svcName;

    @Column(name = "RES_DATE")
    private Date resDate;

    @Column(name = "SYS_ID")
    private String sysId;

    @Lob
    @Column(name = "RES_MSG")
    private String resMsg;

    @Column(name = "MSG_TYPE")
    private String msgType;

    @Column(name = "UPDATED_DATE")
    private Timestamp lastUpdatedDate;

    @Column(name = "UPDATED_BY")
    private String lastUpdatedBy;

	/**
	 * @return the resId
	 */
	public Long getResId() {
		return resId;
	}

	/**
	 * @param resId the resId to set
	 */
	public void setResId(Long resId) {
		this.resId = resId;
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
	 * @return the resDate
	 */
	public Date getResDate() {
		return resDate;
	}

	/**
	 * @param resDate the resDate to set
	 */
	public void setResDate(Date resDate) {
		this.resDate = resDate;
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
	 * @return the resMsg
	 */
	public String getResMsg() {
		return resMsg;
	}

	/**
	 * @param resMsg the resMsg to set
	 */
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
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
}
