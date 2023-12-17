/**
 * 
 */
package com.jonevu.abi.logger.service;

import java.util.concurrent.CompletableFuture;

import com.jonevu.abi.logger.model.AsyncLogServiceResponse;


/**
 * @author jonev
 *
 */
public interface AsyncLoggerResponseService {
	
	CompletableFuture<AsyncLogServiceResponse> create(AsyncLogServiceResponse response);
	
	CompletableFuture<AsyncLogServiceResponse> update(AsyncLogServiceResponse response);
}
