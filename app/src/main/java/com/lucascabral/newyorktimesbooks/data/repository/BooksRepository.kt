package com.lucascabral.newyorktimesbooks.data.repository

import com.lucascabral.newyorktimesbooks.data.BooksResult

interface BooksRepository {
    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}