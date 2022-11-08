package com.konradjurkowski.rickandmorty.android.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.konradjurkowski.rickandmorty.android.presentation.character_detail.CharacterDetailScreen
import com.konradjurkowski.rickandmorty.android.presentation.character_list.CharacterListScreen
import com.konradjurkowski.rickandmorty.android.presentation.navigation.NavigationArguments.CHARACTER_ID
import com.konradjurkowski.rickandmorty.android.presentation.navigation.NavigationArguments.CHARACTER_NAME

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Screen.CharacterList.route) {
        composable(
            route = Screen.CharacterList.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            }
        ) {
            CharacterListScreen(navController = navController)
        }
        composable(
            route = Screen.CharacterDetail.route + "/{$CHARACTER_ID}" + "/{$CHARACTER_NAME}",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
            arguments = listOf(
                navArgument(CHARACTER_ID) {
                    type = NavType.IntType
                },
                navArgument(CHARACTER_NAME) {
                    type = NavType.StringType
                }
            )
        ) {
            CharacterDetailScreen()
        }
    }
}