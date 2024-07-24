package com.StoreBackend.StoreMain.Respository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StoreBackend.StoreMain.Model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

    Boolean existsByUsername(String username);
}
