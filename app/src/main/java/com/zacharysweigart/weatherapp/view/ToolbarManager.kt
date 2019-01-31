package com.zacharysweigart.weatherapp.view

import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.zacharysweigart.weatherapp.*
import com.zacharysweigart.weatherapp.settings.SettingsActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

interface ToolbarManager {
    val toolbar: Toolbar
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }
    var toolbarSubtitle: String
        get() = toolbar.subtitle.toString()
        set(value) {
            toolbar.subtitle = value
        }

    fun initToolbarMenu(activity: AppCompatActivity) {
        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx().startActivity<SettingsActivity>()
                else -> MainApp.instance.toast("Unknown menu item clicked")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = with(DrawerArrowDrawable(toolbar.ctx())) {
        progress = 1f
        this
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else if (dy < 0) toolbar.slideEnter()
            }
        })
    }
}
