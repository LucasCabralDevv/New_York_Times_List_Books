package com.lucascabral.newyorktimesbooks.view.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity: AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, titleIdRes: Int) {
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
    }
}