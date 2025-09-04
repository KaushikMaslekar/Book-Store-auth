package com.kaushik.api.bookstore.entity;

public record Book(String bookId, String name,
        String price, String author, String description) {

}
