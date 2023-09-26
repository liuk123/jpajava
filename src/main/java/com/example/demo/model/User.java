package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="User")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@NamedEntityGraph(
        name = "UserEntity",
        attributeNodes = {
                @NamedAttributeNode("userContributions")
        }
)
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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    List<UserContribution> userContributions;

    @Builder.Default
    @Transient
    private Map<String, Object> metadata = new HashMap<>();
    @Builder.Default
    @Transient
    private List<String> contributions = new ArrayList<>();
}


enum Gender {
    MAIL("男"),FEMALE("女");
    private Gender(String value){
    }
}