package com.mvi.sharednotes.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseOutlinedTextField(
    value: String,
    isLoading: Boolean,
    hasError: Boolean,
    errorMessage: String,
    label: String,
    placeholder: String,
    icon: ImageVector?,
    userInputHandler: (String) -> Unit,
    onDoneClickListener: () -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        enabled = !isLoading,
        modifier = modifier,
        isError = hasError,
        supportingText = {
            if (hasError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        shape = RoundedCornerShape(5.dp, 5.dp, 0.dp, 0.dp),
        value = value,
        leadingIcon = { icon?.let { Icon(imageVector = icon, contentDescription = null) } },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { onDoneClickListener() }),
        onValueChange = { userInputHandler(it) },
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) }
    )
}