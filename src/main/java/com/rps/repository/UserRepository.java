package com.rps.repository;

import com.rps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(String uuid);
    Optional<User> findUserById(Long id);
    Optional<User> findByUsername(String username);

    boolean existsByUuid(String username);
    boolean existsByUsername(String username);
}
