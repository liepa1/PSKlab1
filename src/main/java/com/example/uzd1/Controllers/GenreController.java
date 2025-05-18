package com.example.uzd1.Controllers;

import com.example.uzd1.MyBatis.Genre;
import com.example.uzd1.MyBatis.GenreMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/genres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenreController {

    @Inject
    private GenreMapper genreMapper;

    @GET
    public List<Genre> getAllGenres() {
        return genreMapper.selectAll();
    }

    @GET
    @Path("/{id}")
    public Genre getGenreById(@PathParam("id") Long id) {
        return genreMapper.selectByPrimaryKey(id);
    }
    @GET
    @Path("/{book_id}")
    public List<Genre> getGenresByBookId(@PathParam("book_id") Long bookId) {
        return genreMapper.selectByBookId(bookId);
    }

    @POST
    public void createGenre(Genre genre) {
        genreMapper.insert(genre);
    }

    @PUT
    @Path("/{id}")
    public void updateGenre(@PathParam("id") Long id, Genre genre) {
        genre.setId(id); // svarbu priskirti ID
        genreMapper.updateByPrimaryKey(genre);
    }

    @DELETE
    @Path("/{id}")
    public void deleteGenre(@PathParam("id") Long id) {
        genreMapper.deleteByPrimaryKey(id);
    }
}
