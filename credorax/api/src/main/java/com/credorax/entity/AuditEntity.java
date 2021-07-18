package com.credorax.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class AuditEntity {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATED_AT")
    private Date lastModifiedDate;

    @Setter(AccessLevel.NONE)
    @Version
    @Column(name = "VERSION")
    private Long version;
}
