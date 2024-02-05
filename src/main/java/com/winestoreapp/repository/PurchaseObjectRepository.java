package com.winestoreapp.repository;

import com.winestoreapp.model.PurchaseObject;
import com.winestoreapp.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseObjectRepository extends JpaRepository<PurchaseObject, Long> {

//    Optional<PurchaseObject> findById(Long id);

}
