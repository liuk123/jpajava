package com.example.demo.db.model;

import com.example.demo.base.BaseEntity;
import com.example.demo.base.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="user")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@NamedEntityGraphs(value = {
    @NamedEntityGraph(
            name = "UserEntity",
            attributeNodes = {
//                    @NamedAttributeNode(value="userContributions")
            }
    ),
})
public class User extends BaseEntity implements Serializable {

    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date birth;

    private String username;
    private String password;
    private Integer status;


    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id",referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id",referencedColumnName = "id") })
    List<Role> roles;


    @Builder.Default
    @Transient
    private Map<String, Object> metadata = new HashMap<>();
}