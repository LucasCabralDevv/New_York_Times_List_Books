package com.lucascabral.newyorktimesbooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucascabral.newyorktimesbooks.R
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

    private val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()
    var viewFlipper: LiveData<Pair<Int, Int?>> = viewFlipperLiveData

    fun getBooks() {
        val remote = RetrofitClient.createService(NewYorkTimesService::class.java)
        val call: Call<BookBodyResponse> = remote.getBooks()
        call.enqueue(object: Callback<BookBodyResponse>{
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
                        booksLiveData.value = books
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                    }
                    response.code() == 401 -> {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.book_error_401)
                    }
                    else -> {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.book_error_400_generic)
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.book_error_500_generic)
            }

        })
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}