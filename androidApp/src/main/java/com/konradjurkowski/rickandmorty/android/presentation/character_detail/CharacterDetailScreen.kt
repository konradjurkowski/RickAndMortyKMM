package com.konradjurkowski.rickandmorty.android.presentation.character_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konradjurkowski.rickandmorty.android.R
import com.konradjurkowski.rickandmorty.android.presentation.character_detail.components.InfoItem
import com.konradjurkowski.rickandmorty.android.presentation.character_detail.components.SectionTitle
import com.konradjurkowski.rickandmorty.android.presentation.components.FailedToLoadData
import com.konradjurkowski.rickandmorty.android.presentation.components.ImageItem
import com.konradjurkowski.rickandmorty.android.presentation.components.LoadingProgressBar
import com.konradjurkowski.rickandmorty.domain.model.Status

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val state = viewModel.state
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.regular_padding)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.pageTitle,
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        if (state.character == null && state.isLoading) {
            LoadingProgressBar()
        } else if (state.character == null && state.error != null) {
            FailedToLoadData(message = state.error!!)
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(dimensionResource(id = R.dimen.regular_padding))
            ) {
                SectionTitle(title = stringResource(id = R.string.appearance_label))
                ImageItem(
                    imageUrl = state.character!!.image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.4f)
                        .padding(bottom = dimensionResource(id = R.dimen.regular_padding))
                )
                SectionTitle(title = stringResource(id = R.string.info_label))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 8.dp
                ) {
                    Column {
                        InfoItem(
                            leadingIcon = when(state.character!!.status) {
                                Status.ALIVE -> Icons.Default.Check
                                Status.DEAD -> Icons.Default.Dangerous
                                Status.UNKNOWN -> Icons.Default.QuestionMark
                            },
                            leadingIconTint = when(state.character!!.status) {
                                Status.ALIVE -> Color.Green
                                Status.DEAD -> MaterialTheme.colors.error
                                Status.UNKNOWN -> Color.Gray
                            },
                            leadingText = stringResource(id = R.string.status_label),
                            trailingText = state.character!!.status.value
                        )
                        InfoItem(
                            leadingIcon = Icons.Default.Category,
                            leadingText = stringResource(id = R.string.species_label),
                            trailingText = state.character!!.species
                        )
                        InfoItem(
                            leadingIcon = Icons.Default.Man,
                            leadingText = stringResource(id = R.string.gender_label),
                            trailingText = state.character!!.gender.value
                        )
                        InfoItem(
                            leadingIcon = Icons.Default.Home,
                            leadingText = stringResource(id = R.string.origin_label),
                            trailingText = state.character!!.origin
                        )
                        InfoItem(
                            leadingIcon = Icons.Default.LocationOn,
                            leadingText = stringResource(id = R.string.location_label),
                            trailingText = state.character!!.location
                        )
                        InfoItem(
                            leadingIcon = Icons.Default.Movie,
                            leadingText = stringResource(id = R.string.episodes_label),
                            trailingText = state.character!!.episode.size.toString()
                        )
                    }
                }
            }
        }
    }
}