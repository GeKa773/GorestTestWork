package com.gekaradchenko.goresttestwork.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}