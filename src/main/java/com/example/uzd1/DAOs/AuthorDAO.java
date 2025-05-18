package com.example.uzd1.DAOs;

import com.example.uzd1.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class AuthorDAO {

    @PersistenceContext
    private EntityManager em;


    public void save(Author author) {
        em.persist(author);
    }

    public void printTables() {
        List<String> tables = em.createNativeQuery("SHOW TABLES").getResultList();
        for (String table : tables) {
            System.out.println("Table: " + table);
        }
    }

    public java.util.List<Author> findAll() {
        return em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    public Author getById(Long authorId){
        return em.createQuery("select a FROM Author a WHERE a.id =: authorId", Author.class).
                setParameter("authorId", authorId).getSingleResult();
    }

}
