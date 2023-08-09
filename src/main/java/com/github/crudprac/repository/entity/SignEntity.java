package com.github.crudprac.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="id")
@Table(name="signs")
public class SignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sign_id")
    private int id;

    @Column(name="email", length = 20, unique = true, nullable = false)
    private String email;

    @Column(name="password", length = 100, nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
}
