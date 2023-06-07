package org.example.dao;

import org.example.entity.Autor;
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
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Autor> getAuthors() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Autor> query = currentSession.createQuery(" from Autor", Autor.class);
        return query.getResultList();
    }

    @Override
    public void saveAuthor(Autor autor) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(autor);
    }

    @Override
    public Set<Autor> getAuthorsByIds(List<Integer> authorsIds) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Autor> query = currentSession.createQuery(" from Autor as a where a.id in (:ids)", Autor.class).setParameterList("ids", authorsIds);
        Set<Autor> authors = query.getResultStream().collect(Collectors.toSet());
        return authors;
    }
}
