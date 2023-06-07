package org.example.services;

import org.example.dao.BookDAO;
import org.example.dao.CategoryDAO;
import org.example.entity.Ksiazka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    @Transactional
    public List<Ksiazka> getBooks() {
        List<Ksiazka> books = bookDAO.getBooks();
        return books;
    }

    @Override
    @Transactional
    public void saveBook(Ksiazka ksiazka) {
        //List<Kategoria> kategorie = categoryDAO.getCategories();
        //ksiazka.setKategoria(kategorie.get(0));
        bookDAO.saveBook(ksiazka);
    }

    @Override
    @Transactional
    public Ksiazka getBook(int id) {
        return bookDAO.getBook(id);
    }

    @Override
    @Transactional
    public void deleteBook(Ksiazka ksiazka) {
        bookDAO.deleteBook(ksiazka);
    }

    @Override
    @Transactional
    public List<Ksiazka> getBooksByIds(List<Integer> bookIds) {
        return bookDAO.getBooksByIds(bookIds);
    }
}
