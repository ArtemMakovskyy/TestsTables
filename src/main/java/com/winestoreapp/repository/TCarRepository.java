package com.winestoreapp.repository;

import com.winestoreapp.transaction.TCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCarRepository extends JpaRepository<TCar,Long> {
}
