package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Permission")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Permission extends BaseEntity implements Serializable {

    private String permission;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
//    private List<Abac> abacs;

}
