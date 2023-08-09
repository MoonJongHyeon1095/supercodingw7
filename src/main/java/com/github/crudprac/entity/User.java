package com.github.crudprac.entity;

import com.github.crudprac.util.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "users")
@Entity
public class User extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(columnDefinition = "NVARCHAR(20)", nullable = false)
    private String username; // 아이디

    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String password;

    @Column(columnDefinition = "NVARCHAR(20)",nullable = false)
    private String email;

//    @Column(nullable = false, unique = true)
//    private String nickname;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;

    /* 회원정보 수정 */
//    public void modify(String nickname, String password) {
//        this.nickname = nickname;
//        this.password = password;
//    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서
     * 기존 데이터를 보존하도록 예외처리 */
//    public User updateModifiedDate() {
//        this.onPreUpdate();
//        return this;
//    }
//
//    public String getRoleValue() {
//        return this.role.getValue();
//    }
}