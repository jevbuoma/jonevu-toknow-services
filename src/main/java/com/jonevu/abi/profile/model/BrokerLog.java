package com.jonevu.abi.profile.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="LOGS")
public class BrokerLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "LOG_ID")
    private Long logId;
    
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Column(name = "LOG_TYPE")
    private String logType;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "MESSAGE")
    private String message;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BrokerLog{" +
                "logId=" + logId +
                ", createdDate=" + createdDate +
                ", logType='" + logType + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
