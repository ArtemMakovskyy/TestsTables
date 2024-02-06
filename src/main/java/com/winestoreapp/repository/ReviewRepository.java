package com.winestoreapp.repository;

import com.winestoreapp.model.Order;
import com.winestoreapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    Optional<PurchaseObject> findById(Long id);

}
