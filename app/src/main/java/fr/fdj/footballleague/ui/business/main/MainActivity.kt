package fr.fdj.footballleague.ui.business.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import fr.fdj.footballleague.ui.component.AutocompleteTextField
import fr.fdj.footballleague.ui.theme.AppSurface
import fr.fdj.footballleague.ui.theme.AppTheme
import kotlinx.coroutines.launch


/**
 * Activity which displays the main screen of the application, which consists of a search form and
 * the list of teams matching the typed league in the form.
 */
@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            lifecycleScope.launch {
                viewModel.state.collect { state ->
                    setContent {
                        MainScreen(state = state)
                    }
                }
            }

        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.FetchLeagues)
        }
    }
}