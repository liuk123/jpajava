package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="User")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {

    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date birth;

    private String LoginName;
    private String password;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    List<UserContribution> userContributions;

    @Builder.Default
    @Transient
    private Map<String, Object> metadata = new HashMap<>();
    @Builder.Default
    @Transient
    private List<String> contributions = new ArrayList<>();
}


enum Gender {
    MAIL("男"),FMAIL("女");
    private String value;
    private Gender(String value){
        this.value = value;
    }
}