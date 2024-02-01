package com.winestoreapp.repository;

import com.winestoreapp.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface WineRepository extends JpaRepository<Wine, Long> {

}
