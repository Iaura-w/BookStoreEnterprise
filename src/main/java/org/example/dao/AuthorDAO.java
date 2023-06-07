package org.example.dao;

import org.example.entity.Autor;

import java.util.List;
import java.util.Set;

public interface AuthorDAO {
    List<Autor> getAuthors();

    void saveAuthor(Autor autor);

    Set<Autor> getAuthorsByIds(List<Integer> authorsIds);
}
