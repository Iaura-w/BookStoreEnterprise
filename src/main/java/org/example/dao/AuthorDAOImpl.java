package org.example.dao;

import org.example.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    private SessionFactory sessionFactory;

    public AuthorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Author> getAuthors() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Author> query = currentSession.createQuery(" from Author", Author.class);
        return query.getResultList();
    }

    @Override
    public void saveAuthor(Author author) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(author);
    }

    @Override
    public Set<Author> getAuthorsByIds(List<Integer> authorsIds) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Author> query = currentSession.createQuery(" from Author as a where a.id in (:ids)", Author.class).setParameterList("ids", authorsIds);
        Set<Author> authors = query.getResultStream().collect(Collectors.toSet());
        return authors;
    }
}
