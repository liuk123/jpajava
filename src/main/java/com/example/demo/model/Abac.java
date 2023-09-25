package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Abac")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Abac extends BaseEntity implements Serializable {

    private String expression;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Permission> permissions;

    public Abac(Long id, String expression, List<Permission> permission){
        this.id = id;
        this.permissions = permission;
        this.expression = expression;
    }
}
