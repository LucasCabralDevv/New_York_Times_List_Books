package com.lucascabral.newyorktimesbooks.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucascabral.newyorktimesbooks.R
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar()
    }

    private fun setupToolbar() {
        toolbarMain.title = getString(R.string.toolbarMain_title)
        setSupportActionBar(toolbarMain)
    }
}