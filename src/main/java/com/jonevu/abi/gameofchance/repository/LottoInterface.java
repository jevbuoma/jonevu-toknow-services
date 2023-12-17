/**
 * 
 */
package com.jonevu.abi.gameofchance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonevu.abi.gameofchance.model.LottoTracking;


/**
 * @author jonev
 *
 */
public interface LottoInterface extends JpaRepository<LottoTracking, Long> {

}
