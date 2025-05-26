package com.example.uzd1.services;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.example.uzd1.DAOs.BookDAO;
import com.example.uzd1.entities.Book;

import java.util.List;

@RequestScoped
public class BookService {

    @Inject
    private BookDAO bookDao;


    @Transactional
    public void saveBook(Book book){
        bookDao.save(book);
    }

    public List<Book> findByAuthor(Long id){

        return bookDao.findByAuthorId(id);
    }
}
