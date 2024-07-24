package com.StoreBackend.StoreMain.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.StoreBackend.StoreMain.Model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByName(String name);
}
