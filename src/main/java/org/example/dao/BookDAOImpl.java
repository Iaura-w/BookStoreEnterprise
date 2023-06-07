package org.example.dao;

import org.example.entity.Autor;
import org.example.entity.Ksiazka;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Ksiazka> getBooks() {
        //sesja hibertabe
        Session currentSession = sessionFactory.getCurrentSession();
        //zapytanie
        Query<Ksiazka> query = currentSession.createQuery("SELECT DISTINCT k from Ksiazka k LEFT JOIN FETCH k.autorzy", Ksiazka.class);
        List<Ksiazka> books = query.getResultList();

        return books;
    }

    @Override
    public void saveBook(Ksiazka ksiazka) {
        Session session = sessionFactory.getCurrentSession();
        // ksiazka.setKategoria();
        session.saveOrUpdate(ksiazka);
    }

    @Override
    public Ksiazka getBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Ksiazka.class, id);
    }

    @Override
    public void deleteBook(Ksiazka ksiazka) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(ksiazka);
    }

//    @Override
//    public Set<Ksiazka> getBooksByIds(List<Integer> bookIds) {
//        Session currentSession = sessionFactory.getCurrentSession();
//        //Query<Ksiazka> query = currentSession.createQuery(" from Ksiazka as k WHERE k.id in (:ids)", Ksiazka.class).setParameterList("ids", bookIds);
//        Query<Ksiazka> query = currentSession.createQuery(" from Ksiazka as a where a.id in (:ids)", Ksiazka.class).setParameterList("ids", bookIds);
//        Set<Ksiazka> books = query.getResultStream().collect(Collectors.toSet());
//
//        return books;
//    }

    @Override
    public List<Ksiazka> getBooksByIds(List<Integer> bookIds) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Ksiazka> query = currentSession.createQuery("select distinct b from Ksiazka b " +   "left join fetch b.autorzy where b.id in (:ids)", Ksiazka.class).setParameterList("ids", bookIds);
        return query.getResultList();
    }
}
