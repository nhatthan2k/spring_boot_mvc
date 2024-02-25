package com.ra.demomvcboot.service;

import com.ra.demomvcboot.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category findById(Long id);
    Category save(Category category);
    void delete(Long id);
}
