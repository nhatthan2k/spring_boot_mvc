package com.ra.demomvcboot.model.repository;

import com.ra.demomvcboot.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
