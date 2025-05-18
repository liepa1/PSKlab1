package com.example.uzd1.MyBatis;

import com.example.uzd1.MyBatis.Genre;

import org.mybatis.cdi.Mapper;
import java.util.List;

@Mapper
public interface GenreMapper {
    int deleteByPrimaryKey(Long id);

    List<Genre> selectByBookId(Long bookId);

    int insert(Genre record);

    Genre selectByPrimaryKey(Long id);

    List<Genre> selectAll();

    int updateByPrimaryKey(Genre record);
}