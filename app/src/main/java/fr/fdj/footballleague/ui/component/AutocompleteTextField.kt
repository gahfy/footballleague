package fr.fdj.footballleague.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.fdj.footballleague.R

@Composable
fun AutocompleteTextField() {
    Text(
        text = stringResource(id = R.string.search_by_league)
    )
}

@Composable
@Preview
fun AutocompletePreview() {
    AutocompleteTextField()
}