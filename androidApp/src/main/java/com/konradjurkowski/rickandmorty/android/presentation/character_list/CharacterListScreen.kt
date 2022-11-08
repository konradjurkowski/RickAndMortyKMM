package com.konradjurkowski.rickandmorty.android.presentation.character_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.konradjurkowski.rickandmorty.android.R
import com.konradjurkowski.rickandmorty.android.presentation.character_list.components.CharacterCard
import com.konradjurkowski.rickandmorty.android.presentation.character_list.components.CharacterCardPlaceholder
import com.konradjurkowski.rickandmorty.android.presentation.components.FailedToLoadData
import com.konradjurkowski.rickandmorty.android.presentation.components.InputTextField
import com.konradjurkowski.rickandmorty.android.presentation.components.LoadingProgressBar
import com.konradjurkowski.rickandmorty.android.presentation.navigation.Screen
import com.konradjurkowski.rickandmorty.android.util.vibrateClick
import com.konradjurkowski.rickandmorty.presentation.character_list.CharacterListEvent
import com.konradjurkowski.rickandmorty.util.Constants

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val state = viewModel.state
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo), 
                    contentDescription = null,
                    modifier = Modifier
                        .height(screenHeight * 0.1f)
                        .padding(dimensionResource(id = R.dimen.small_padding))
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.regular_padding))
        ) {
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.regular_padding)),
                text = state.query,
                onTextChange = {
                    viewModel.onTriggerEvents(CharacterListEvent.OnUpdateQuery(it))
                },
                placeholder = stringResource(id = R.string.search_label),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_icon),
                        modifier = Modifier
                            .vibrateClick {
                                viewModel.onTriggerEvents(CharacterListEvent.NewSearch)
                                keyboardController?.hide()
                            }
                    )
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.onTriggerEvents(CharacterListEvent.NewSearch)
                        keyboardController?.hide()
                    }
                )
            )
            if (state.isLoading && state.characters.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn {
                        items(5) {
                            CharacterCardPlaceholder()
                        }
                    }
                    LoadingProgressBar()
                }
            }
            if (state.error != null && !state.isLoading) {
                FailedToLoadData(
                    message = state.error!!
                )
            }
            if (state.characters.isNotEmpty()) {
                LazyColumn {
                    itemsIndexed(items = state.characters) { index, character ->
                        if ((index + 1) >= (state.page * Constants.PAGE_SIZE) && !state.isLoading) {
                            viewModel.onTriggerEvents(CharacterListEvent.NewPage)
                        }
                        CharacterCard(
                            character = character,
                            onClick = { item ->
                                navController.navigate(Screen.CharacterDetail.route + "/${item.id}" + "/${item.name}")
                            }
                        )
                    }
                }
            }
        }
        if (state.isLoading && state.characters.isNotEmpty()) {
            LoadingProgressBar()
        }
    }
}