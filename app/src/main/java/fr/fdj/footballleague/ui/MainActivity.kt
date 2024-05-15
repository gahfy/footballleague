package fr.fdj.footballleague.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint


/**
 * Activity which displays the main screen of the application, which consists of a search form and
 * the list of teams matching the typed league in the form.
 */
@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchAllLeagues()
    }
}