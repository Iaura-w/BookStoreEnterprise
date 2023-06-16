package org.example.services;

import org.example.dao.CategoryDAO;
import org.example.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        categoryDAO.saveCategory(category);
    }

    @Override
    @Transactional
    public Category getCategory(int id) {
        return categoryDAO.getCategory(id);
    }

}
