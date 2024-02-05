package com.winestoreapp.repository;

import com.winestoreapp.model.User;
import com.winestoreapp.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
