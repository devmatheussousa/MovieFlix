package com.movieflix.demo.repository;

import com.movieflix.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Metodo para encontrar um usuário pelo email, que é o username nesse caso
    Optional<User> findUserByEmail(String email);
}
