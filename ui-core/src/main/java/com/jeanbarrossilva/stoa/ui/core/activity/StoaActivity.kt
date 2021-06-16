package com.jeanbarrossilva.stoa.ui.core.activity

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.jeanbarrossilva.stoa.extensions.context.activity.view
import com.jeanbarrossilva.stoa.extensions.view.drawerlayout.toggle
import com.jeanbarrossilva.stoa.extensions.view.searchFor
import com.jeanbarrossilva.stoa.ui.core.R

open class StoaActivity: AppCompatActivity() {
    private fun configNavigation() {
        val toolbar = view?.searchFor<Toolbar>()
        val drawerLayout = view?.searchFor<DrawerLayout>()

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(findNavController(), drawerLayout)
        toolbar?.setNavigationOnClickListener {
            drawerLayout?.toggle()
        }
    }

    override fun onStart() {
        super.onStart()
        configNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) findNavController().popBackStack() else super.onOptionsItemSelected(item)
    }

    fun findNavController(): NavController {
        return findNavController(R.id.container)
    }
}