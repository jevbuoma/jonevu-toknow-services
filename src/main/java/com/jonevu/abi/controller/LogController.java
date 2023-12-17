/**
 * 
 */
package com.jonevu.abi.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import com.jonevu.abi.logger.model.AsyncLogServiceRequest;
import com.jonevu.abi.logger.model.AsyncLogServiceResponse;
import com.jonevu.abi.logger.service.AsyncLoggerRequestService;
import com.jonevu.abi.logger.service.AsyncLoggerResponseService;
import com.jonevu.abi.util.CommonUtil;
import com.jonevu.abi.util.DateUtil;

/**
 * @author jonev
 *
 */
public abstract class LogController {
	
    @Autowired
    AsyncLoggerRequestService asyncLoggerRequestService;
    
    @Autowired
    AsyncLoggerResponseService asyncLoggerResponseService;

    /**
     *
     * @param request
     * @return
     */
    public CompletableFuture<AsyncLogServiceRequest> createRequest(AsyncLogServiceRequest request) {
    	return asyncLoggerRequestService.create(request);
    }

    /**
     * 
       @param payload
     * @param msgType
     * @param sysIdPrefix
     */
    public CompletableFuture<AsyncLogServiceResponse> createResponse(long reqId, String payload, String msgType,
                                                                     String sysIdPrefix, String serviceName) {
    	// ...
    	String sysId = formatSysId(sysIdPrefix);
    	AsyncLogServiceResponse response = new AsyncLogServiceResponse();
    	response.setReqId(reqId);
    	response.setMsgType(msgType);
    	response.setLastUpdatedBy(System.getProperty("user.name"));
    	response.setSysId(sysId);
    	response.setSvcName(serviceName);
    	response.setLastUpdatedDate(DateUtil.getCurrentDate());
    	response.setResDate(DateUtil.getCurrentDate());
    	response.setResMsg(payload);
    	
		return asyncLoggerResponseService.create(response);
    }

    /**
     * 
     * @param payload
     * @param msgType
     * @param serviceName
     * @param sysIdPrefix
     * @return
     */
    public AsyncLogServiceRequest loadRequest(String payload, String msgType, String serviceName, String sysIdPrefix) {
    	String sysId = formatSysId(sysIdPrefix);
    	AsyncLogServiceRequest request = getNewAsyncServiceRequest();
    	request.setLastUpdatedBy(System.getProperty("user.name"));
    	request.setSvcName(serviceName);
    	request.setSysId(sysId);
    	request.setLastUpdatedDate(DateUtil.getCurrentDate());
    	request.setReqDate(DateUtil.getCurrentDate());
    	request.setMsgType(msgType);
    	request.setReqMsg(payload);
    	request.setReqId(CommonUtil.generateRandomNumber());

    	return request;
    }   

    private AsyncLogServiceRequest getNewAsyncServiceRequest() {
        return new AsyncLogServiceRequest();
    }

    private String formatSysId(String sysIdPrefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(sysIdPrefix).append("-").append(String.valueOf(CommonUtil.generateRandomNumber()).substring(0, 10));
        return sb.toString();
    }
}
