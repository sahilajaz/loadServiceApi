package com.example.loadService.repo;

import com.example.loadService.model.LoadService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoaderServiceRepo extends JpaRepository<LoadService, Long> {
    LoadService findByShipperId(String shipperId);
}
