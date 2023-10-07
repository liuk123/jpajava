package com.example.demo.db.model;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity
@Table(name="Permission")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Permission extends BaseEntity implements Serializable {

    private String permission;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
//    private List<Abac> abacs;

    public Permission(Long id){
        this.id = id;
    }

}
