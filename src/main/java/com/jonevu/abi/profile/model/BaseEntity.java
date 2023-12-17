package com.jonevu.abi.profile.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.jonevu.abi.profile.conversion.TimestampAdapter;

/**
 * Entity implementation class for Entity: BaseEntity
 */
@XmlAccessorType(XmlAccessType.FIELD)
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Column(name="TYT_SOURCE")
    private String source;

    @XmlJavaTypeAdapter( TimestampAdapter.class)
    @Column(name="TYT_CREATED_DT")
    private Timestamp createdDt;

    @Column(name="TYT_LAST_UPDATED_BY")
    private Long lastUpdatedBy;

    @XmlJavaTypeAdapter( TimestampAdapter.class)
    @Column(name="TYT_LAST_UPDATED_DT")
    private Timestamp lastUpdatedDt;

    private static final long serialVersionUID = 1L;

    public BaseEntity() {
        super();
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "source='" + source + '\'' +
                ", createdDt=" + createdDt +
                ", lastUpdatedBy=" + lastUpdatedBy +
                ", lastUpdatedDt=" + lastUpdatedDt +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Timestamp getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Timestamp createdDt) {
        this.createdDt = createdDt;
    }

    public Timestamp getLastUpdatedDt() {
        return lastUpdatedDt;
    }

    public void setLastUpdatedDt(Timestamp lastUpdatedDt) {
        this.lastUpdatedDt = lastUpdatedDt;
    }
}
