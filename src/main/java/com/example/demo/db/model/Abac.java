package com.example.demo.db.model;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="abac")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Abac extends BaseEntity implements Serializable {

    private String expression;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "abac_permissions",
        joinColumns = {
                @JoinColumn(name = "abac_id",referencedColumnName = "id") },
        inverseJoinColumns = {
                @JoinColumn(name = "permissions_id",referencedColumnName = "id") })
    public List<Permission> permissions;
    public Abac(Long id, String expression, List<Permission> permission){
        this.id = id;
        this.permissions = permission;
        this.expression = expression;
    }
}
