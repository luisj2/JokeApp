package com.xluis.adviceapp.Presentation.Screens.MainScreen


import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.xluis.adviceapp.Presentation.Navitgation.PrincipalScreen

@Composable
fun MainScreen(
    screenContent: @Composable (screenSelected: PrincipalScreen) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    MainScreenContent(
        screenContent = screenContent,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun MainScreenContent(
    screenContent: @Composable (PrincipalScreen) -> Unit,
    uiState: MainScreenUiState,
    onEvent: (MainScreenUiEvent) -> Unit
) {
    val screens = PrincipalScreen.entries

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                screens = screens,
                screenSelected = uiState.screenSelected,
                onScreenSelected = { screen ->
                    onEvent(MainScreenUiEvent.ChangeScreenClick(screen))
                }
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {

            AnimatedContent(
                targetState = uiState.screenSelected,
                label = "MainScreenAnimation"
            ) { targetScreen ->
                screenContent(targetScreen)
            }

        }
    }
}

@Composable
private fun BottomNavigationBar(
    screens: List<PrincipalScreen>,
    screenSelected: PrincipalScreen,
    onScreenSelected: (PrincipalScreen) -> Unit
) {
    NavigationBar {

        screens.forEach { screen ->
            val selected = screen == screenSelected

            NavigationBarItem(
                selected = selected,
                onClick = { onScreenSelected(screen) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = { Text(screen.title) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    }
}