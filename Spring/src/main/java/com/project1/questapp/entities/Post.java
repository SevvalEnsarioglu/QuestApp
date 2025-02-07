package com.project1.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="post")
@Data
public class Post{
    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //post many ancak 1 user var olduğu için bu ilişki many to one'dır.
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User userId;
    // Long userId;

    String title;
    @Lob
    @Column(columnDefinition="text")
    String text;

}
