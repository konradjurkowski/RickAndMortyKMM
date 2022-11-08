package com.konradjurkowski.rickandmorty.android.presentation.character_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.konradjurkowski.rickandmorty.android.R

@Composable
fun CharacterCardPlaceholder(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.regular_padding)),
        elevation = 8.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(screenHeight * 0.15f)
                    .padding(dimensionResource(id = R.dimen.regular_padding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.regular_padding)))
                    .placeholder(
                        visible = true,
                        color = Color.Gray,
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = Color.White
                        )
                    )
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.regular_padding)))
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.character_name_placeholder),
                    style = MaterialTheme.typography.h3,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.Gray,
                            shape = MaterialTheme.shapes.small,
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = Color.White
                            )
                        )
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.character_status_placeholder),
                        style = MaterialTheme.typography.h4,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .placeholder(
                                visible = true,
                                color = Color.Gray,
                                shape = MaterialTheme.shapes.small,
                                highlight = PlaceholderHighlight.shimmer(
                                    highlightColor = Color.White
                                )
                            )
                    )
                }
            }
        }
    }
}