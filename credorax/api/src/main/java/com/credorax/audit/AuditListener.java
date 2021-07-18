package com.credorax.audit;

import com.credorax.enums.Topic;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;


@NoArgsConstructor
@AllArgsConstructor
public class AuditListener {

    private final Logger LOG = LoggerFactory.getLogger(AuditListener.class);

    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostUpdate
    private void beforeUpdate(Object object) {
        kafkaTemplate.send(Topic.AUDIT.name(), (object));
    }

    @PostPersist
    private void beforePersist(Object object) {
        kafkaTemplate.send(Topic.AUDIT.getName(), object);
    }

    @PostRemove
    private void beforeDelete(Object object) {
        kafkaTemplate.send(Topic.AUDIT.name(), object);
    }
}
