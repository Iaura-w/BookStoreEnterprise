package org.example.services;

import org.example.entity.Kategoria;

import java.util.List;

public interface CategoryService {
    List<Kategoria> getCategories();

    void addCategory(Kategoria kategoria);

    Kategoria getCategory(int id);
}
