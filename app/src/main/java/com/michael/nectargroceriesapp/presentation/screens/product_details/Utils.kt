package com.michael.nectargroceriesapp.presentation.screens.product_details

import android.content.Context
import android.content.Intent

fun shareTextWithImageLink(context: Context, text: String, imageUrl: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(
            Intent.EXTRA_TEXT,
            "$text\n\nCheck this out: $imageUrl"
        )
    }

    context.startActivity(
        Intent.createChooser(shareIntent, "Share via")
    )
}
