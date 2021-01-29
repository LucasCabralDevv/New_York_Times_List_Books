package com.lucascabral.newyorktimesbooks.data

import com.lucascabral.newyorktimesbooks.data.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
    class ApiError(val statusCode: Int) : BooksResult()
    object ServerError : BooksResult()
}