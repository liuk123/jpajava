package com.example.demo.db.model;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="comment")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Comment extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    @ManyToOne(cascade = {}, optional=false)
    @JoinColumn(name="from_user_id")
    private User fromUser;

    @OneToMany(mappedBy = "comment",cascade={  }, fetch=FetchType.LAZY)
    @OrderBy("createTime ASC")
    private List<Reply> replyList;

}
