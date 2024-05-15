package fr.fdj.footballleague.ui.business.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import fr.fdj.footballleague.ui.component.AutocompleteTextField
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

        setContent {
            AutocompleteTextField()
        }

        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.FetchLeagues)
        }
    }
}