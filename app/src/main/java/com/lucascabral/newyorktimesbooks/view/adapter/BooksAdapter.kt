package com.lucascabral.newyorktimesbooks.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucascabral.newyorktimesbooks.R
import com.lucascabral.newyorktimesbooks.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(
        private val books: List<Book>
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount() = books.count()

    inner class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.bookTitleTextView
        private val author = itemView.bookAuthorTextView

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author
        }
    }
}