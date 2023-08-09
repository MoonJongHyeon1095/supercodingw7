package com.github.crudprac.dto.user;

import com.github.crudprac.entity.User;
import lombok.Getter;

import java.io.Serializable;
public class UserResponseDto implements Serializable{

    @Getter
    public static class Response implements Serializable {

        private final Integer id;
        private final String username;
        //        private final String nickname;
        private final String email;
        //        private final Role role;
        private final String modifiedDate;

        /* Entity -> dto */
        public Response(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
//            this.nickname = user.getNickname();
            this.email = user.getEmail();
//            this.role = user.getRole();
            this.modifiedDate = user.getModifiedDate();
        }
    }
}
