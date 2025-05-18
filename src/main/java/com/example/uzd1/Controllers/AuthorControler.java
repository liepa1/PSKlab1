package com.example.uzd1.Controllers;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.uzd1.entities.Author;
import com.example.uzd1.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;

@WebServlet("/author")
public class AuthorControler extends HttpServlet {

    @Inject
    private AuthorService authorService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Author> authors = authorService.findAllAuthors();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        new ObjectMapper().writeValue(response.getWriter(), authors);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the author's name from the form
        String name = request.getParameter("name");
        System.out.println("name: " + name);

        // Create a new Author object and set the name
        Author newAuthor = new Author(name);
        System.out.println("New Author: " + newAuthor);


        // Save the new author using AuthorService
        authorService.saveAuthor(newAuthor);

        response.setContentType("application/json");
        response.setContentType("application/json");
        response.getWriter().write("{\"id\":" + newAuthor.getId() + ",\"name\":\"" + newAuthor.getName() + "\"}");


    }


}


