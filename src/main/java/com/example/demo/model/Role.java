package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Role")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity{
    private String name;
    private String description;

    @ManyToMany(mappedBy = "roles")
    List<UserContribution> userContributions;
}
