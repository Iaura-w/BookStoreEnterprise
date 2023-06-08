package org.example.dao;

import org.example.entity.Author;

import java.util.List;
import java.util.Set;

public interface AuthorDAO {
    List<Author> getAuthors();

    void saveAuthor(Author author);

    Set<Author> getAuthorsByIds(List<Integer> authorsIds);

    void deleteAuthor(Author author);

    Author getAuthor(int id);
}
