package org.example.dao;

import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final SessionFactory sessionFactory;

    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Book> getBooks() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Book> query = currentSession.createQuery("SELECT DISTINCT k from Book k LEFT JOIN FETCH k.authors", Book.class);
        List<Book> books = query.getResultList();

        return books;
    }

    @Override
    public void saveBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
    }

    @Override
    public Book getBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Override
    public void deleteBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(book);
    }

    @Override
    public List<Book> getBooksByIds(List<Integer> bookIds) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Book> query = currentSession.createQuery("select distinct b from Book b " + "left join fetch b.authors where b.id in (:ids)", Book.class).setParameterList("ids", bookIds);
        return query.getResultList();
    }
}
