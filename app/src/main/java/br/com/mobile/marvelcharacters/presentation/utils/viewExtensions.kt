package br.com.mobile.marvelcharacters.presentation.utils

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ShimmerFrameLayout.startShimmering() {
    this.startShimmer()
    this.show()
}

fun ShimmerFrameLayout.stopShimmering() {
    this.stopShimmer()
    this.hide()
}