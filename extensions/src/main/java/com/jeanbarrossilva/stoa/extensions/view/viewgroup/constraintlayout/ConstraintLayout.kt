package com.jeanbarrossilva.stoa.extensions.view.viewgroup.constraintlayout

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.jeanbarrossilva.stoa.extensions.view.viewgroup.identifyUnidentifiedChildren

/**
 * Applies the given [constraint] to this [ConstraintLayout], targeting [target]. Handles calls to [ConstraintSet.clone] and
 * [ConstraintSet.applyTo].
 */
fun ConstraintLayout.constrain(target: View, constraint: ConstraintSet.(targetId: Int) -> Unit): ConstraintSet {
    identifyUnidentifiedChildren()
    return ConstraintSet().apply {
        clone(this@constrain)
        constraint(target.id)
        applyTo(this@constrain)
    }
}