/**
 * 
 */
package com.jonevu.abi.logger.exception;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jonev
 *
 */
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);
	
	@Override
    public void handleUncaughtException(final Throwable throwable, final Method method, final Object... obj) {
		logger.info("Exception message - " + throwable.getMessage());
		logger.info("Method name - " + method.getName());
        for (final Object param : obj) {
        	logger.info("Param - " + param);
        }
    }
}

