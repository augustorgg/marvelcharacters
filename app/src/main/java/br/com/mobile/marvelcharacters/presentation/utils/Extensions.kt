package br.com.mobile.marvelcharacters.presentation.utils

import android.view.View
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
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

fun CharacterResult?.getSecureImageUrl(): String? {
    return this?.thumbnail?.let { thumbnail ->
        val imageUrl = "${thumbnail.path}.${thumbnail.extension}"
        imageUrl.replace("http://", "https://")
    }
}

fun String?.orIfNullOrEmpty(defaultValue: String): String {
    return if (this.isNullOrEmpty()) defaultValue else this
}
