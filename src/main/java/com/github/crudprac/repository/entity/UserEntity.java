package com.github.crudprac.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="id")
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="email", length = 20 ,unique = true, nullable = false)
    private String email;

    @Column(name="password", length = 100, nullable = false)
    private String password;

    @Column(name="username", length = 20, nullable = false)
    private String name;
}
