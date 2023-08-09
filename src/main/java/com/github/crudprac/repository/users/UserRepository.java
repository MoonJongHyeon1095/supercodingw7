package com.github.crudprac.repository.users;

import com.github.crudprac.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>  {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
