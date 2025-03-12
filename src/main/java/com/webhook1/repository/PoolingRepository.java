package com.webhook1.repository;

import com.webhook1.entity.Pooling;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PoolingRepository extends MongoRepository<Pooling, String> {
}
