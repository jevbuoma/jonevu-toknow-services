/**
 * 
 */
package com.jonevu.abi.logger.service;

import java.util.concurrent.CompletableFuture;

import com.jonevu.abi.logger.model.AsyncLogServiceRequest;

/**
 * @author jonev
 *
 */
public interface AsyncLoggerRequestService {
	
	CompletableFuture<AsyncLogServiceRequest> create(AsyncLogServiceRequest request);
	
	CompletableFuture<AsyncLogServiceRequest> update(AsyncLogServiceRequest request);

}
