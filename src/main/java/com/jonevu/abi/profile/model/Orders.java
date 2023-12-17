package com.jonevu.abi.profile.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.jonevu.abi.profile.conversion.TimestampAdapter;


/**
 * @author jonev
 * 
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "OE_ORDERS")
@SequenceGenerator(name = "ORDR_SEQ", sequenceName = "OE_ORDERS_ID", initialValue = 1, allocationSize = 1)
public class Orders extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2732993670795136617L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqGen")
    @SequenceGenerator(name = "SeqGen", sequenceName = "ORDERS_SEQUENCE", allocationSize = 1)
    @Column(name = "OrderId")
    private Long orderId;

    @XmlJavaTypeAdapter( TimestampAdapter.class)
    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @Column(name = "ORDER_MODE")
    private String orderMode;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

/*    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "CUSTOMER_ID", insertable=false, updatable=false)
    private Customers customers;
*/
    
    @Column(name = "ORDER_STATUS")
    private Integer orderStatus;

    @Column(name = "ORDER_TOTAL")
    private BigDecimal orderTotal;

    @Column(name = "SALES_REP_ID")
    private Long salesRepId;

    @Column(name = "PROMOTION_ID")
    private Long promotionId;

    @OneToMany(mappedBy = "customerId")
    private List<Customers> customerList;

/*    
    *//**
     * @return the customers
     *//*
    public Customers getCustomers() {
        return customers;
    }

    *//**
     * @param customers the customers to set
     *//*
    public void setCustomers(Customers customers) {
        this.customers = customers;
    }
*/
    
    /**
     * @return the orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderDate
     */
    public Timestamp getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the orderMode
     */
    public String getOrderMode() {
        return orderMode;
    }

    /**
     * @param orderMode the orderMode to set
     */
    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode;
    }

    /**
     * @return the customerId
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderStatus
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the orderTotal
     */
    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    /**
     * @param orderTotal the orderTotal to set
     */
    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * @return the salesRepId
     */
    public Long getSalesRepId() {
        return salesRepId;
    }

    /**
     * @param salesRepId the salesRepId to set
     */
    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    /**
     * @return the promotionId
     */
    public Long getPromotionId() {
        return promotionId;
    }

    /**
     * @param promotionId the promotionId to set
     */
    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    /**
     *
     * @return
     */
    public List<Customers> getCustomerList() {
        return customerList;
    }

    /**
     *
     * @param customerList
     */
    public void setCustomerList(List<Customers> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderMode='" + orderMode + '\'' +
                ", customerId=" + customerId +
                ", orderStatus=" + orderStatus +
                ", orderTotal=" + orderTotal +
                ", salesRepId=" + salesRepId +
                ", promotionId=" + promotionId +
                ", customerList=" + customerList +
                '}';
    }
}
