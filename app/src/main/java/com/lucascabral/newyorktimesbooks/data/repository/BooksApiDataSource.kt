package com.lucascabral.newyorktimesbooks.data.repository

import com.lucascabral.newyorktimesbooks.R
import com.lucascabral.newyorktimesbooks.data.BooksResult
import com.lucascabral.newyorktimesbooks.data.NewYorkTimesService
import com.lucascabral.newyorktimesbooks.data.RetrofitClient
import com.lucascabral.newyorktimesbooks.data.model.Book
import com.lucascabral.newyorktimesbooks.data.response.BookBodyResponse
import com.lucascabral.newyorktimesbooks.viewmodel.BooksViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BooksRepository {

    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        val remote = RetrofitClient.createService(NewYorkTimesService::class.java)
        val call: Call<BookBodyResponse> = remote.getBooks()
        call.enqueue(object: Callback<BookBodyResponse> {
            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let {
                            for (result in it.bookResults) {
                                val book = result.bookDetailsResponse[0].getBookModel()
                                books.add(book)
                            }
                        }
                        booksResultCallback(BooksResult.Success(books))
                    }
                    else -> {
                        booksResultCallback(BooksResult.ApiError(response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }
        })
    }
}