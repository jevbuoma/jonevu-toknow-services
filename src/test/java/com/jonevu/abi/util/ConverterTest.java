/**
 * 
 */
package com.jonevu.abi.util;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jonevu.abi.profile.model.Orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jonev
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConverterTest {

	private static final Logger logger = LoggerFactory.getLogger(ConverterTest.class);
	
	@SuppressWarnings("unused")
	@Test
	public void convertOrderToXmlTest() throws JsonProcessingException {
		Orders order = createOrder();
		String xmlStr = (String)CommonUtil.convertToXmlJson(order, "json");
		logger.info("Request date: "+(DateUtil.getCurrentDate().toString()).split(" ")[0]);
		DateUtil.convertStrToDate((DateUtil.getCurrentDate().toString()).split(" ")[0], "yyyy-MM-dd");
	}
	
    private Orders createOrder() {
    	BigDecimal bd =  new BigDecimal(1267.94);
        Orders order = new Orders();
        order.setCustomerId(103L);
        order.setOrderDate(DateUtil.getCurrentDate());
        order.setOrderMode("online");
        order.setOrderStatus(6);
        order.setOrderTotal(bd.abs(new MathContext(6)));
        order.setPromotionId(1679L);
        order.setSalesRepId(1009L);
        order.setCreatedDt(DateUtil.getCurrentDate());
        order.setLastUpdatedBy(612L);
        order.setLastUpdatedDt(DateUtil.getCurrentDate());
        order.setSource("JE92");

        return order;
    }    	
}
