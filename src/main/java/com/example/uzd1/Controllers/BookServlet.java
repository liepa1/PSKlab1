package com.example.uzd1.Controllers;


import com.example.uzd1.entities.Author;
import com.example.uzd1.entities.Book;
import com.example.uzd1.services.AuthorService;
import com.example.uzd1.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    @Inject
    private BookService bookService;

    @Inject
    private AuthorService authorService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String authorIdParam = request.getParameter("authorId");

        Long authorId = Long.parseLong(authorIdParam);
        String title = request.getParameter("title");

        if (title == null || authorId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing title or authorId");
            return;
        }

        Book newBook = new Book();
        newBook.setTitle(title);
        Author author = authorService.findAuthorById(authorId);
        newBook.setAuthor(author);
        bookService.saveBook(newBook);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"title\": \"" + newBook.getTitle() + "\"}");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorIdParam = request.getParameter("authorId");

        if (authorIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing authorId parameter");
            return;
        }

        try {
            Long authorId = Long.parseLong(authorIdParam);

            List<Book> books = bookService.findByAuthor(authorId);
            String authorName = authorService.findAuthorNameById(authorId);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("authorName", authorName);
            responseMap.put("books", books);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            new ObjectMapper().writeValue(response.getWriter(), responseMap);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid authorId format");
        }
    }
}


