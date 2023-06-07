package org.example.services;

import org.example.entity.Ksiazka;

import java.util.List;
import java.util.Set;

public interface BookService {
    List<Ksiazka> getBooks();

    void saveBook(Ksiazka ksiazka);

    Ksiazka getBook(int id);

    void deleteBook(Ksiazka ksiazka);

    List<Ksiazka> getBooksByIds(List<Integer> bookIds);
}
