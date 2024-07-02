package com.winestoreapp.model.projection;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryCloseProjection extends JpaRepository<UserCloseProjection, Long> {
    List<UserCloseProjectionShort> findAllProjectedBy();
    Optional<UserCloseProjectionShort> findProjectedById(Long id);
}