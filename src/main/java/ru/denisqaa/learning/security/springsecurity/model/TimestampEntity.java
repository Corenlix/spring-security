package ru.denisqaa.learning.security.springsecurity.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class TimestampEntity {
    @CreationTimestamp
    @Column(name = "createdat")
    protected Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedat")
    protected Date updatedAt;
}