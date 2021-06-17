package com.jeanbarrossilva.stoa.extensions.view.drawerlayout

import androidx.drawerlayout.widget.DrawerLayout

/**
 * Closes the drawer if it is open, opens it if it is closed.
 *
 * @see DrawerLayout.open
 * @see DrawerLayout.close
 **/
fun DrawerLayout.toggle() {
    if (isOpen) close() else open()
}