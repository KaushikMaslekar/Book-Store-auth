package com.kaushik.api.bookstore.dto;

public record BookDto(String bookId, String name,
        String price, String author, String description) {

}
