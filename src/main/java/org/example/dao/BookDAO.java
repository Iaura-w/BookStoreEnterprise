package org.example.dao;

import org.example.entity.Ksiazka;

import java.util.List;
import java.util.Set;

public interface BookDAO {
    List<Ksiazka> getBooks();

    void saveBook(Ksiazka ksiazka);

    Ksiazka getBook(int id);

    void deleteBook(Ksiazka ksiazka);

    List<Ksiazka> getBooksByIds(List<Integer> bookIds);
}
