package fr.fdj.footballleague.ui.business.main

import androidx.compose.ui.text.input.TextFieldValue
import fr.fdj.footballleague.model.League

sealed class MainState {
    data object LoadingState: MainState()
    data class LeaguesRetrievedState(
        val textValue: TextFieldValue,
        val onValueChanges: (TextFieldValue) -> Unit,
        val menuValues: List<League>,
        val onMenuItemSelected: (League) -> Unit
    ): MainState()

    data class ErrorState(
        val errorMessage: String
    ): MainState()
}