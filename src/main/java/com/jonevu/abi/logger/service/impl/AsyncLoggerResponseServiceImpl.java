/**
 * 
 */
package com.jonevu.abi.logger.service.impl;

import java.util.concurrent.CompletableFuture;

import com.jonevu.abi.logger.repository.AsyncLogServiceResponseRepository;
import com.jonevu.abi.logger.service.AsyncLoggerResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jonevu.abi.logger.model.AsyncLogServiceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author jonev
 *
 */
@Service
public class AsyncLoggerResponseServiceImpl implements AsyncLoggerResponseService {

	private static final Logger logger = LoggerFactory.getLogger(AsyncLoggerResponseServiceImpl.class);
	
	private AsyncLogServiceResponseRepository asyncLogServiceResponseRepository;
	
	@Autowired
	public AsyncLoggerResponseServiceImpl(AsyncLogServiceResponseRepository asyncLogServiceResponseRepository) {
		this.asyncLogServiceResponseRepository = asyncLogServiceResponseRepository;
	}
	
	/* (non-Javadoc)
	 * @see com.jonevu.abi.log.svc.service.AsyncLoggerResponseService#create(com.jonevu.abi.log.svc.model.AsyncLogServiceResponse)
	 */
	@Async("asyncLoggerExecutor")
	@Override
	public CompletableFuture<AsyncLogServiceResponse> create(AsyncLogServiceResponse response) {
		response = asyncLogServiceResponseRepository.saveAndFlush(response);
		logger.info("Inside create response method.. requestId="+response.getResId());
		return CompletableFuture.completedFuture(response);
	}

	/* (non-Javadoc)
	 * @see com.jonevu.abi.log.svc.service.AsyncLoggerResponseService#update(com.jonevu.abi.log.svc.model.AsyncLogServiceResponse)
	 */
	@Async("asyncLoggerExecutor")
	@Override
	public CompletableFuture<AsyncLogServiceResponse> update(AsyncLogServiceResponse response) {
		response = asyncLogServiceResponseRepository.saveAndFlush(response);
		logger.info("Inside update response method.. requestId="+response.getResId());
		return CompletableFuture.completedFuture(response);
	}
}
