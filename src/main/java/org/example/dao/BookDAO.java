package org.example.dao;

import org.example.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBooks();

    void saveBook(Book book);

    Book getBook(int id);

    void deleteBook(Book book);

    List<Book> getBooksByIds(List<Integer> bookIds);
}
