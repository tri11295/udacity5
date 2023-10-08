package com.example.android.moviedb.extensions

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun View.isVisible() = visibility == View.VISIBLE
fun View.isGone() = visibility == View.GONE
fun View.isInvisible() = visibility == View.INVISIBLE

fun View.show() {
    if (!isVisible()) visibility = View.VISIBLE
}

fun View.hide() {
    if (!isInvisible()) visibility = View.INVISIBLE
}

fun View.gone() {
    if (!isGone()) visibility = View.GONE
}

fun View.isVisible(visible: Boolean) {
    if (visible) show() else gone()
}

fun ImageView.loadFromUrl(url: String) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .skipMemoryCache(false)
        .into(this)
}

fun RatingBar.setVoteRating(voteAverage: Double?) {
    this.rating = voteAverage?.div(2)?.toFloat() ?: -1f
}
