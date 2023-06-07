package org.example.services;

import org.example.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    void addCategory(Category category);

    Category getCategory(int id);
}
