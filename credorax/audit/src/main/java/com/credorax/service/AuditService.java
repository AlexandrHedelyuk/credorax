package com.credorax.service;

import com.credorax.repository.AuditRepository;
import com.credorax.repository.impl.AuditRepositoryImpl;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    private final AuditRepository repository;

    public AuditService(AuditRepositoryImpl repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "audit", groupId = "audit")
    public void auditListener(ConsumerRecord<Long, Object> record) {
        repository.save(record.value().toString());
    }
}
