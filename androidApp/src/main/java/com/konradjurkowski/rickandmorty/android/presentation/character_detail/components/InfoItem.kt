package com.konradjurkowski.rickandmorty.android.presentation.character_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import com.konradjurkowski.rickandmorty.android.R

@Composable
fun InfoItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    leadingIconTint: Color = Color.White,
    leadingText: String,
    trailingText: String
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.small_padding)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = leadingText,
                    tint = leadingIconTint
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
                Text(
                    text = leadingText,
                    style = MaterialTheme.typography.h4.copy(
                        color = Color.White
                    ),
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = trailingText,
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.primary
                ),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
        Divider()
    }
}