/**
 * 
 */
package com.jonevu.abi.logger.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonevu.abi.logger.model.AsyncLogServiceResponse;

/**
 * @author jonev
 *
 */
@Transactional
public interface AsyncLogServiceResponseRepository extends JpaRepository<AsyncLogServiceResponse, Long>{

}
