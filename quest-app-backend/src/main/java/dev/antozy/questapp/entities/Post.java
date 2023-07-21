package dev.antozy.questapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    Long id;
    Long userId;
    String title;

    @Lob
    @Column(columnDefinition = "text")
    String text;
}
