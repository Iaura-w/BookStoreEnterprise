package org.example.dao;

import org.example.entity.Kategoria;

import java.util.List;

public interface CategoryDAO {
    List<Kategoria> getCategories();

    void saveCategory(Kategoria kategoria);

    Kategoria getCategory(int id);
}
