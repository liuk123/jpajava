package com.example.demo.base;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(name = "create_time", updatable = false)
    @CreationTimestamp
    public Date createTime;
    @Column(name = "update_time")
    @UpdateTimestamp
    public Date updateTime;
}
