package com.dnk.assignment.domain

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "timestamp not null")
    val createdAt: LocalDateTime = LocalDateTime.MAX,

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp not null")
    val updatedAt: LocalDateTime = LocalDateTime.MAX
)