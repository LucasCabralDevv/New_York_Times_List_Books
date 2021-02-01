package com.lucascabral.newyorktimesbooks.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lucascabral.newyorktimesbooks.R
import com.lucascabral.newyorktimesbooks.data.BooksResult
import com.lucascabral.newyorktimesbooks.data.model.Book
import com.lucascabral.newyorktimesbooks.data.repository.BooksRepository
import com.lucascabral.newyorktimesbooks.viewmodel.BooksViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    /* @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    } */

    private lateinit var viewModel: BooksViewModel

    // Primeira função de teste
    @Test
    fun `when view model getBooks get success then sets booksLiveData`() {
        //Arrange
        val books = listOf<Book>(
            Book("Title 1", "Author 1", "Description 1"),
            Book("Title 2", "Author 2", "Description 2")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.books.observeForever(booksLiveDataObserver)
        viewModel.viewFlipper.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()

        //Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getBooks get server error then sets booksLiveData`() {
        //Arrange
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)
        viewModel.viewFlipper.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()
        
        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.book_error_500_generic))
    }
}

class MockRepository(private val result: BooksResult) : BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }

}