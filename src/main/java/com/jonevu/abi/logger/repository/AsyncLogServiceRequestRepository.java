/**
 * 
 */
package com.jonevu.abi.logger.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonevu.abi.logger.model.AsyncLogServiceRequest;

/**
 * @author jonev
 *
 */
@Transactional
public interface AsyncLogServiceRequestRepository extends JpaRepository<AsyncLogServiceRequest, Long> {

}
