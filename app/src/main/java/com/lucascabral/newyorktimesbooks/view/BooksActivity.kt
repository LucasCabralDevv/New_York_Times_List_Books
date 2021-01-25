package com.lucascabral.newyorktimesbooks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascabral.newyorktimesbooks.R
import com.lucascabral.newyorktimesbooks.view.adapter.BooksAdapter
import com.lucascabral.newyorktimesbooks.view.base.BaseActivity
import com.lucascabral.newyorktimesbooks.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {

    private lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.toolbarMain_home_title)

        viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
        viewModel.books.observe(this, Observer {
            it?.let {
                with(booksRecyclerView) {
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(it) {
                        val intent = BookDetailsActivity.getStartIntent(this@BooksActivity, it.title, it.description)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })
        viewModel.getBooks()
    }
}