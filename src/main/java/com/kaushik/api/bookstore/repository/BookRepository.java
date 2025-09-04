package com.kaushik.api.bookstore.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.kaushik.api.bookstore.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{bookId : '?0'}")
    Book findBookByBookId(String bookId);

    @Query(value = "{ 'bookId' : { $eq : ?0} }") // Find book by bookId
    @Update(pipeline = {" { '$set' : { 'name' : ?1 }}"}) // Update book name by bookId
    void updateBookNameByBookId(String bookId, String name);

    @DeleteQuery
    void deleteBookByBookId(String bookId);

}
