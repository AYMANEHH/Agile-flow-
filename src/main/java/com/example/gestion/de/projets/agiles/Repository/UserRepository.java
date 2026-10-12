
package com.example.gestion.de.projets.agiles.Repository;

import com.example.gestion.de.projets.agiles.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
