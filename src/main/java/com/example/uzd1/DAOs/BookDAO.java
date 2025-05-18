package com.example.uzd1.DAOs;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.example.uzd1.entities.Book;

import java.util.List;

@ApplicationScoped
public class BookDAO {

    @PersistenceContext
    private EntityManager em;

    public void save(Book book) {
        em.persist(book);
    }

    public List<Book> findByAuthorId(Long authorId) {
        return em.createQuery("SELECT b from Book b WHERE b.author.id =: authorId", Book.class).
                setParameter("authorId", authorId).getResultList();
    }



}