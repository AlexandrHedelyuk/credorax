package com.credorax.repository.impl;

import com.credorax.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Repository
public class AuditRepositoryImpl implements AuditRepository {

    private final Logger LOG = LoggerFactory.getLogger(AuditRepositoryImpl.class);

    @Value("${audit.log.path}")
    private String path;

    @Override
    public void save(String json) {

        try (FileWriter fileWriter = new FileWriter(path + "audit.json", true)) {

            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(json);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            LOG.error("Can't save audit logs ", e);
            e.printStackTrace();
        }
    }
}
