package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Map;

@Entity
@Table(name="Dictionary")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Dictionary extends BaseEntity{


    @ElementCollection
    @Column(name = "value")
    private Map<String, String> values;
}
