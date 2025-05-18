package com.example.uzd1.services;

import javax.enterprise.context.RequestScoped;
import com.example.uzd1.DAOs.AuthorDAO;
import com.example.uzd1.entities.Author;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;
import javax.transaction.Transactional;

@RequestScoped
public class AuthorService {

    @Inject
    private AuthorDAO authorDao;

    @Transactional
    public void saveAuthor(Author author) {
        authorDao.save(author);
    }

    public List<Author> findAllAuthors() {
        return authorDao.findAll();
    }

    public Author findAuthorById(Long authorId){
        return authorDao.getById(authorId);
    }

    public String findAuthorNameById(Long authorId){
        return authorDao.getById(authorId).getName();
    }
}
