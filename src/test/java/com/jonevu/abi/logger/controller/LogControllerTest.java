/**
 * 
 */
package com.jonevu.abi.logger.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.CompletableFuture;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jonevu.abi.controller.LogController;
import com.jonevu.abi.logger.model.AsyncLogServiceRequest;
import com.jonevu.abi.logger.service.AsyncLoggerRequestService;
import com.jonevu.abi.logger.service.AsyncLoggerResponseService;
import com.jonevu.abi.profile.model.Orders;
import com.jonevu.abi.util.CommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jonev
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogControllerTest extends LogController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogControllerTest.class);
	
    @Autowired
    AsyncLoggerRequestService asyncLoggerRequestService;
    
    @Autowired
    AsyncLoggerResponseService asyncLoggerResponseService;

   
    @SuppressWarnings("unused")
	@Test
    @Ignore
    public void logRequestTest() throws JsonProcessingException {
    	Orders order = createOrder();
    	String jsonStr = (String)CommonUtil.convertToXmlJson(order, "jspn");
    	AsyncLogServiceRequest request = loadRequest(jsonStr, "json", "Profile", "prof");
    	logger.info("Request ID: "+request.getReqId());
    	//CompletableFuture<AsyncLogServiceRequest> _request = asyncLoggerRequestService.create(request);
    	CompletableFuture<AsyncLogServiceRequest> _request = createRequest(request);
    }
    
    private Orders createOrder() {
    	BigDecimal bd =  new BigDecimal(1267.94);
        Orders order = new Orders();
        order.setCustomerId(103L);
        order.setOrderDate(null);
        order.setOrderMode("online");
        order.setOrderStatus(6);
        order.setOrderTotal(bd.abs(new MathContext(6)));
        order.setPromotionId(1679L);
        order.setSalesRepId(1009L);
        order.setCreatedDt(null);
        order.setLastUpdatedBy(612L);
        order.setLastUpdatedDt(null);
        order.setSource("JE92");

        return order;
    }    
}
