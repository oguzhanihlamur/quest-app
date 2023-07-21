package dev.antozy.questapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "post_like")
@Data
public class Like {

    @Id
    Long id;
    Long postId;
    Long userId;
}
