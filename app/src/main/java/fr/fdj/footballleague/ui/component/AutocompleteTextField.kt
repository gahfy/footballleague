package fr.fdj.footballleague.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.fdj.footballleague.R
import fr.fdj.footballleague.ui.theme.AppSurface
import fr.fdj.footballleague.ui.theme.AppTheme

@Composable
fun AutocompleteTextField() {
    Box {
        Text(
            text = stringResource(id = R.string.search_by_league)
        )
    }
}

@Composable
@Preview
fun AutocompletePreview() {
    AppTheme {
        AppSurface {
            AutocompleteTextField()
        }
    }
}