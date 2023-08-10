package com.github.crudprac.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="id")
@ToString
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @Column(name="email", length = 20 ,unique = true, nullable = false)
    private String email;

    @Column(name="password", length = 100, nullable = false)
    private String password;

    @Column(name="username", length = 20, nullable = false)
    private String name;

    @OneToMany
    private List<AuthorityEntity> authorities;

    @OneToMany(mappedBy = "user")
    private final List<PostEntity> posts = new ArrayList<>();
}
