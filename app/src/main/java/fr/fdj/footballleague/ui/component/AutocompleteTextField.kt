package fr.fdj.footballleague.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import fr.fdj.footballleague.ui.theme.AppSurface
import fr.fdj.footballleague.ui.theme.AppTheme

@Composable
fun <T> AutocompleteTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    menuValues: List<T>,
    onMenuItemSelected: (T) -> Unit
) {
    var editComesFromMenu by remember { mutableStateOf(false) }
    var menuExpanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val onTextValueChanged: (TextFieldValue) -> Unit = { currentValue ->
        if(!editComesFromMenu) {
            menuExpanded = true
        }
        onValueChange(currentValue)
        if(editComesFromMenu) {
            editComesFromMenu = false
            focusManager.clearFocus()
        }
    }
    Column {

        TextField(
            modifier = Modifier.onFocusChanged { focusState ->
                if(focusState.hasFocus) {
                    menuExpanded = true
                } else {
                    menuExpanded = false
                }
            }.fillMaxWidth(),
            value = value,
            onValueChange = onTextValueChanged
        )
        Box {
            DropdownMenu(
                expanded = menuExpanded,
                properties = PopupProperties(
                    focusable = false,
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                ),
                onDismissRequest = {
                    menuExpanded = false
                }
            ) {
                LazyColumn(
                    modifier = Modifier.height(300.dp)
                        .width(500.dp)
                ) {
                    items(menuValues.size) {
                        DropdownMenuItem(
                            text = { Text(menuValues[it].toString()) },
                            onClick = {
                                editComesFromMenu = true
                                menuExpanded = false
                                onMenuItemSelected(menuValues[it])
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun AutocompletePreview() {
    var value by remember {
        mutableStateOf(
            TextFieldValue("")
        )
    }
    AppTheme {
        AppSurface {
            AutocompleteTextField(
                value = value,
                onValueChange = { value = it },
                menuValues = listOf(
                    Pair("Test1", "Test1"),
                    Pair("Test1", "Test1"),
                    Pair("Test1", "Test1")
                ),
                onMenuItemSelected = {
                    value = TextFieldValue(it.toString())
                }
            )
        }
    }
}