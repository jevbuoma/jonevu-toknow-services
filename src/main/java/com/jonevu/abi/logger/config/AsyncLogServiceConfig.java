/**
 * 
 */
package com.jonevu.abi.logger.config;

import java.util.concurrent.Executor;

import com.jonevu.abi.logger.exception.CustomAsyncExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jonev
 *
 */
@Configuration
@EnableAsync
public class AsyncLogServiceConfig implements AsyncConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(AsyncLogServiceConfig.class);
	
	@Value("${executor.core.pool.size}")
	private int corePoolSize;
	
	@Value("${executor.max.pool.size}")
	private int maxPoolSize;
	
	@Value("${executor.queue.capacity}")
	private int queueCpacity;
	
	@Value("${executor.thread.name.prefix}")
	private String threadNamePrefix;

    @Bean(name = "asyncLoggerExecutor")
    public Executor asyncExecutor() {
     	logger.info("Activating async logger ..."+new java.util.Date().toString());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCpacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }	
    
    @Bean
    public Executor asyncLoggerPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Override
    public Executor getAsyncExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }     
}
