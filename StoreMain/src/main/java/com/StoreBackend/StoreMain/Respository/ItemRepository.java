package com.StoreBackend.StoreMain.Respository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StoreBackend.StoreMain.Model.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items, Integer> {

    Optional<Items> findByItemName(String itemName);

}
