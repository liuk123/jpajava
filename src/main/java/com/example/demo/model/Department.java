package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="Department")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@ToString(exclude = {})
public class Department extends BaseEntity{

    private Long parentId;
    private String name;
    private String description;
}
