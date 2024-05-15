package fr.fdj.footballleague.ui.business.main

import androidx.compose.runtime.Composable
import fr.fdj.footballleague.ui.component.AutocompletePreview
import fr.fdj.footballleague.ui.component.AutocompleteTextField

@Composable
fun MainScreen(
    state: MainState
) {
    when(state) {
        is MainState.LoadingState -> MainLoadingScreen()
        is MainState.LeaguesRetrievedState -> MainSuccessScreen(state)
        is MainState.ErrorState -> MainErrorScreen()
    }
}

@Composable
private fun MainLoadingScreen() {

}

@Composable
private fun MainSuccessScreen(
    state: MainState.LeaguesRetrievedState
) {
    AutocompleteTextField(
        value = state.textValue,
        onValueChange = state.onValueChanges,
        menuValues = state.menuValues,
        onMenuItemSelected = state.onMenuItemSelected
    )
}

@Composable
private fun MainErrorScreen() {

}