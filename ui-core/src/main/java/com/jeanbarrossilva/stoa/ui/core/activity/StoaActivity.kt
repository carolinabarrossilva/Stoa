package com.jeanbarrossilva.stoa.ui.core.activity

import android.graphics.drawable.ColorDrawable
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.jeanbarrossilva.stoa.extensions.context.activity.view
import com.jeanbarrossilva.stoa.extensions.context.colorOf
import com.jeanbarrossilva.stoa.extensions.context.isSystemInDarkTheme
import com.jeanbarrossilva.stoa.extensions.view.searchFor
import com.jeanbarrossilva.stoa.extensions.view.viewgroup.drawerlayout.toggle
import com.jeanbarrossilva.stoa.extensions.window.insetsControllerCompat
import com.jeanbarrossilva.stoa.ui.core.R
import com.jeanbarrossilva.stoa.ui.core.view.StoaToolbar

open class StoaActivity: AppCompatActivity() {
    private val toolbar by lazy {
        view?.searchFor<StoaToolbar>()
    }

    private fun configSystemBarsColor() {
        window.insetsControllerCompat.isAppearanceLightStatusBars = !isSystemInDarkTheme
        window.insetsControllerCompat.isAppearanceLightNavigationBars = !isSystemInDarkTheme
        window.statusBarColor = (toolbar?.background as? ColorDrawable)?.color ?: colorOf(android.R.attr.colorBackground)
        window.navigationBarColor = colorOf(android.R.attr.colorBackground)
    }

    private fun configNavigation() {
        val drawerLayout = view?.searchFor<DrawerLayout>()
        if (toolbar != null && drawerLayout != null) {
            setSupportActionBar(toolbar!!.androidToolbar)
            setupActionBarWithNavController(findNavController(), drawerLayout)
            findNavController().addOnDestinationChangedListener { controller, destination, _ ->
                toolbar?.setNavigationOnClickListener {
                    if (destination.id == controller.graph.startDestination)
                        drawerLayout.toggle()
                    else
                        findNavController().popBackStack()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        configSystemBarsColor()
        configNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) findNavController().popBackStack() else super.onOptionsItemSelected(item)
    }

    fun findNavController(): NavController {
        return findNavController(R.id.container)
    }
}