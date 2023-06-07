package org.example.services;

import org.example.entity.Author;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    List<Author> getAuthors();

    void saveAuthor(Author author);

    Set<Author> getAuthorsByIds(List<Integer> authorsIds);
}
