package com.webhook1.repository;

import com.webhook1.entity.Sweeping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SweepingRepository extends MongoRepository<Sweeping, String> {
}
