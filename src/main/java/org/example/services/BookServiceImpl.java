package org.example.services;

import org.example.dao.BookDAO;
import org.example.dao.CategoryDAO;
import org.example.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    @Transactional
    public List<Book> getBooks() {
        List<Book> books = bookDAO.getBooks();
        return books;
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        //List<Category> kategorie = categoryDAO.getCategories();
        //book.setCategory(kategorie.get(0));
        bookDAO.saveBook(book);
    }

    @Override
    @Transactional
    public Book getBook(int id) {
        return bookDAO.getBook(id);
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        bookDAO.deleteBook(book);
    }

    @Override
    @Transactional
    public List<Book> getBooks(List<Integer> bookIds) {
        return bookDAO.getBooksByIds(bookIds);
    }
}
