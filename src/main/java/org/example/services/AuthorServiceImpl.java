package org.example.services;

import org.example.dao.AuthorDAO;
import org.example.entity.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDAO authorDAO;

    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    @Transactional
    public List<Author> getAuthors() {
        return authorDAO.getAuthors();
    }

    @Override
    @Transactional
    public void saveAuthor(Author author) {
        authorDAO.saveAuthor(author);
    }

    @Override
    @Transactional
    public Set<Author> getAuthorsByIds(List<Integer> authorsIds) {
        return authorDAO.getAuthorsByIds(authorsIds);
    }

    @Override
    @Transactional
    public void deleteAuthor(Author author) {
        authorDAO.deleteAuthor(author);
    }

    @Override
    @Transactional
    public Author getAuthor(int id) {
        return authorDAO.getAuthor(id);
    }
}
