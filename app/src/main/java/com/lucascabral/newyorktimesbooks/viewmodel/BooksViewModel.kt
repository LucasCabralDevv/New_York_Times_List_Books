package com.lucascabral.newyorktimesbooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucascabral.newyorktimesbooks.data.model.Book

class BooksViewModel : ViewModel() {

    private val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    var books: LiveData<List<Book>> = booksLiveData

    fun getBooks() {
        booksLiveData.value = createFakeBooks()
    }

    private fun createFakeBooks(): List<Book> {
        return listOf(
            Book("Title1", "Author1"),
            Book("Title2", "Author2"),
            Book("Title3", "Author3"),
            Book("Title4", "Author4"),
            Book("Title8", "Author8")
        )
    }
}