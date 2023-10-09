package com.example.demo.db.model;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="menu")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@NamedEntityGraph(
        name = "MenuEntity",
        attributeNodes = {
                @NamedAttributeNode("permissions")
        }
)
public class Menu extends BaseEntity implements Serializable {

    private String url;
    private String method;

    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "menu_permission",
        joinColumns = {
                @JoinColumn(name = "menu_id",referencedColumnName = "id") },
        inverseJoinColumns = {
                @JoinColumn(name = "permission_id",referencedColumnName = "id") })
    public List<Permission> permissions;
}
