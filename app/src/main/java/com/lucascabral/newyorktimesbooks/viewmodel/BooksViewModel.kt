package com.lucascabral.newyorktimesbooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucascabral.newyorktimesbooks.data.NewYorkTimesService
import com.lucascabral.newyorktimesbooks.data.RetrofitClient
import com.lucascabral.newyorktimesbooks.data.model.Book
import com.lucascabral.newyorktimesbooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    private val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    var books: LiveData<List<Book>> = booksLiveData

    fun getBooks() {
        val remote = RetrofitClient.createService(NewYorkTimesService::class.java)
        val call: Call<BookBodyResponse> = remote.getBooks()
        call.enqueue(object: Callback<BookBodyResponse>{
            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                if (response.isSuccessful) {
                    val books: MutableList<Book> = mutableListOf()

                    response.body()?.let {
                        for (result in it.bookResults) {
                            val book = Book(
                              title = result.bookDetailsResponse[0].title,
                              author = result.bookDetailsResponse[0].author,
                              description = result.bookDetailsResponse[0].description
                            )

                            books.add(book)
                        }
                    }
                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                val str = ""
            }

        })
    }
}