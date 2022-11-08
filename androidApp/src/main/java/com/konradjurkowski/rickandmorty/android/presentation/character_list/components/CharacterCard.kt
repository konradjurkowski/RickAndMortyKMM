package com.konradjurkowski.rickandmorty.android.presentation.character_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.QuestionMark
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
import com.konradjurkowski.rickandmorty.android.R
import com.konradjurkowski.rickandmorty.android.presentation.components.ImageItem
import com.konradjurkowski.rickandmorty.domain.model.Character
import com.konradjurkowski.rickandmorty.domain.model.Status

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: (Character) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.regular_padding)),
        elevation = 8.dp,
        onClick = {
            onClick(character)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageItem(
                imageUrl = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(screenHeight * 0.15f)
                    .padding(dimensionResource(id = R.dimen.regular_padding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.regular_padding)))
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.regular_padding)))
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h3,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_padding)))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${stringResource(id = R.string.status)} ${character.status.value}",
                        style = MaterialTheme.typography.h4,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_padding)))
                    Icon(
                        imageVector = when(character.status) {
                            Status.ALIVE -> Icons.Default.Check
                            Status.DEAD -> Icons.Default.Dangerous
                            Status.UNKNOWN -> Icons.Default.QuestionMark
                        },
                        tint = when(character.status) {
                            Status.ALIVE -> Color.Green
                            Status.DEAD -> MaterialTheme.colors.error
                            Status.UNKNOWN -> Color.Gray
                        },
                        contentDescription = character.name
                    )
                }
            }
        }
    }
}