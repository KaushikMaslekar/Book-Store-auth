package com.kaushik.api.bookstore.service;

import java.util.List;

import com.kaushik.api.bookstore.dto.BookDto;

public interface BookService {

    public BookDto getBook(String bookId);

    public List<BookDto> getAllBooks();

    public BookDto createBook(BookDto bookDto);

    public BookDto updateBookName(BookDto bookDto);

    public void deleteBookByBookId(String bookId);
}
