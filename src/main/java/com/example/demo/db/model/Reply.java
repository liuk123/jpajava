package com.example.demo.db.model;

import com.example.demo.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity
@Table(name="reply")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Reply extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(cascade = {})
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @ManyToOne(cascade = {})
    @JoinColumn(name="to_user_id")
    private User toUser;

    @ManyToOne(cascade = {})
    private Comment comment;
}
