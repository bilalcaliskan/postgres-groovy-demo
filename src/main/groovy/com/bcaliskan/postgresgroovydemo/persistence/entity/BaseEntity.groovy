package com.bcaliskan.postgresgroovydemo.persistence.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import java.time.LocalDateTime



@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @CreatedDate
    LocalDateTime createdAt

    @LastModifiedDate
    LocalDateTime updatedAt

}
