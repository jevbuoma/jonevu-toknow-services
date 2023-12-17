package com.jonevu.abi.profile.repository;

import org.springframework.data.repository.CrudRepository;

import com.jonevu.abi.profile.model.BrokerLog;

public interface AppLogRepository extends CrudRepository<BrokerLog, Long> {
}
