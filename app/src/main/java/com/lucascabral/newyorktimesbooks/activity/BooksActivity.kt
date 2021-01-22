package com.lucascabral.newyorktimesbooks.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascabral.newyorktimesbooks.R
import com.lucascabral.newyorktimesbooks.adapter.BooksAdapter
import com.lucascabral.newyorktimesbooks.data.model.Book
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar()

        with(booksRecyclerView) {
            layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = BooksAdapter(getBooks())
        }
    }

    private fun setupToolbar() {
        toolbarMain.title = getString(R.string.toolbarMain_title)
        setSupportActionBar(toolbarMain)
    }

    private fun getBooks(): List<Book> {
        return listOf<Book>(
                Book("Title1", "Author1"),
                Book("Title2", "Author2"),
                Book("Title3", "Author3"),
                Book("Title4", "Author4"),
                Book("Title5", "Author5")
        )
    }
}