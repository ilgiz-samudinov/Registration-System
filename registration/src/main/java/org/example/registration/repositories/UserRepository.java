package org.example.registration.repositories;

import org.example.registration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
