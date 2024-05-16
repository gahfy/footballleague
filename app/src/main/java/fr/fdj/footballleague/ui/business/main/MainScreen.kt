package fr.fdj.footballleague.ui.business.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fr.fdj.footballleague.R
import fr.fdj.footballleague.ui.component.AutocompleteTextField

@Composable
fun MainScreen(
    state: MainState
) {
    when(state) {
        is MainState.LoadingState -> MainLoadingScreen()
        is MainState.LeaguesRetrievedState -> MainSuccessScreen(state)
        is MainState.ErrorState -> MainErrorScreen(state)
    }
}

@Composable
private fun MainLoadingScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun MainSuccessScreen(
    state: MainState.LeaguesRetrievedState
) {
    AutocompleteTextField(
        value = state.textValue,
        onValueChange = state.onValueChanges,
        menuValues = state.menuValues,
        onMenuItemSelected = state.onMenuItemSelected,
        placeholder = stringResource(id = R.string.search_by_league)
    ) {
        if(state.teamsError) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.error)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = state.retryTeams) {
                        Text(text = stringResource(id = R.string.retry))
                    }
                }
            }
        } else {
            if (state.teams.isNotEmpty()) {
                LazyColumn {
                    items(state.teams.size / 2 + 1) {
                        Column {
                            Spacer(modifier = Modifier.height(16.dp))
                            Row {
                                if (it * 2 < state.teams.size) {
                                    Box(
                                        modifier = Modifier.weight(1.0f)
                                    ) {
                                        AsyncImage(
                                            modifier = Modifier.align(Alignment.Center),
                                            model = state.teams[it * 2].badgeUrl,
                                            contentDescription = state.teams[it * 2].name
                                        )
                                    }
                                    Box(
                                        modifier = Modifier.weight(1.0f)
                                    ) {
                                        if (it * 2 + 1 < state.teams.size) {
                                            AsyncImage(
                                                modifier = Modifier.align(Alignment.Center),
                                                model = state.teams[it * 2 + 1].badgeUrl,
                                                contentDescription = state.teams[it * 2 + 1].name
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MainErrorScreen(state: MainState.ErrorState) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.error)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = state.retryLeagues) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}