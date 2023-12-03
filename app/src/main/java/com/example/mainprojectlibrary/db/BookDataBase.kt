package com.example.mainprojectlibrary.db


class BookDataBase : DbWrapper<Book>() {
    override fun getTableName(): String = "Book"
    override fun getClassType(): Class<Book> = Book::class.java
}