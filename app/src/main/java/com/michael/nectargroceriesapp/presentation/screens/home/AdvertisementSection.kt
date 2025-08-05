package com.michael.nectargroceriesapp.presentation.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.michael.nectargroceriesapp.R
import kotlinx.coroutines.delay

@Composable
fun AdvertisementSection(
    modifier: Modifier = Modifier
) {
    val ads = listOf(
        R.drawable.img_banner1,
        R.drawable.img_banner2,
        R.drawable.img_banner3,
    )
    val pageState = rememberPagerState { ads.size }

    LaunchedEffect(Unit) {
        while (true){
            delay(2000)
            pageState.animateScrollToPage(
                page = (pageState.currentPage + 1) % ads.size
            )
        }
    }

    Box(
        modifier = modifier.fillMaxSize().wrapContentHeight()
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) { pageNumber ->
            Card {
                Image(
                    painter = painterResource(ads[pageNumber]),
                    contentDescription = null
                )
            }
        }

        PageIndicator(
            modifier = Modifier.align(Alignment.BottomCenter),
            pageCount = ads.size,
            currentPage = pageState.currentPage
        )
    }
}

@Composable
fun PageIndicator(modifier: Modifier, pageCount: Int, currentPage: Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageCount) {

            val targetColor = if (it == currentPage) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            }
            val animatedColor by animateColorAsState(
                targetValue = targetColor,
                label = "color"
            )

            val targetWidth = if (it == currentPage) 25.dp else 6.dp
            val animatedWidth by animateDpAsState(
                targetValue = targetWidth,
                label = "width"
            )

            DotIndicator(
                color = animatedColor,
                width = animatedWidth,
                height = 6.dp,
                modifier = modifier
            )
        }

    }

}

@Composable
fun DotIndicator(color: Color, width: Dp, height: Dp, modifier: Modifier) {
    Box(
        modifier
            .padding(3.dp, 6.dp)
            .clip(CircleShape)
            .width(width)
            .height(height)
            .background(color)
    ) {

    }
}