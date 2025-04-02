package com.ujjawal.billionhearts.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role


@Composable
inline fun Modifier.noRippleClickable(
    role: Role? = null,
    enabled: Boolean = true,
    crossinline onClick: () -> Unit
): Modifier {
    return this.clickable(
        role = role,
        enabled = enabled,
        interactionSource = remember {
            MutableInteractionSource()
        },
        indication = null,
        onClick = {
            onClick()
        }
    )
}