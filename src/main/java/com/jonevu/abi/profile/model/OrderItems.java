package com.jonevu.abi.profile.model;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.persistence.*;

/**
 * @author jonev
 *
 */
@Entity
@Table(name = "OE.ORDER_ITEMS")
@SequenceGenerator(name = "ORDRITM_SEQ", sequenceName = "OE_ORDERS_ID", initialValue = 1, allocationSize = 1)
public class OrderItems extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2732993670795136617L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "LINE_ITEM_ID")
    private Long lineItemId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Override
    public String toString() {
        return "OrderItemsRepository{" +
                "orderId=" + orderId +
                ", lineItemId=" + lineItemId +
                ", productId=" + productId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }

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
     * @return the lineItemId
     */
    public Long getLineItemId() {
        return lineItemId;
    }

    /**
     * @param lineItemId the lineItemId to set
     */
    public void setLineItemId(Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    /**
     * @return the productId
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
