/**
 * 
 */
package com.jonevu.abi.logger.service.impl;

import java.util.concurrent.CompletableFuture;

import com.jonevu.abi.logger.repository.AsyncLogServiceRequestRepository;
import com.jonevu.abi.logger.service.AsyncLoggerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jonevu.abi.logger.model.AsyncLogServiceRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jonev
 *
 */
@Service
public class AsyncLoggerRequestServiceImpl implements AsyncLoggerRequestService {
	
	private static final Logger logger = LoggerFactory.getLogger(AsyncLoggerRequestServiceImpl.class);

	private AsyncLogServiceRequestRepository asyncLogServiceRequestRepository;
	
	@Autowired
	public AsyncLoggerRequestServiceImpl(AsyncLogServiceRequestRepository asyncLogServiceRequestRepository) {
		this.asyncLogServiceRequestRepository = asyncLogServiceRequestRepository;
	}
	
	/* (non-Javadoc)
	 * @see com.jonevu.abi.log.svc.service.AsyncLoggerRequestService#create(com.jonevu.abi.log.svc.model.AsyncLogServiceRequest)
	 */
	@Async("asyncLoggerExecutor")
	@Override
	public CompletableFuture<AsyncLogServiceRequest> create(AsyncLogServiceRequest request) {
		logger.info("Inside create request method.. requestId="+request.getReqId());
		try {
			request = asyncLogServiceRequestRepository.saveAndFlush(request);
		}
		catch(DataAccessException dae) {
			logger.error("DataAccessException Errror in create request: "+dae.getMessage());
		}
		catch(Exception ex) {
			logger.error("Exception Errror in create request: "+ex.getMessage());
		}
		return CompletableFuture.completedFuture(request);
	}

	/* (non-Javadoc)
	 * @see com.jonevu.abi.log.svc.service.AsyncLoggerRequestService#update(com.jonevu.abi.log.svc.model.AsyncLogServiceRequest)
	 */
	@Async("asyncLoggerExecutor")
	@Override
	public CompletableFuture<AsyncLogServiceRequest> update(AsyncLogServiceRequest request) {
		request = asyncLogServiceRequestRepository.saveAndFlush(request);
		logger.info("Inside update request method.. requestId="+request.getReqId());
		return CompletableFuture.completedFuture(request);
	}
}
