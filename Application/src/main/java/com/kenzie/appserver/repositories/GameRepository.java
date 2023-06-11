package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.GameRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface GameRepository extends CrudRepository<GameRecord, String> {
}