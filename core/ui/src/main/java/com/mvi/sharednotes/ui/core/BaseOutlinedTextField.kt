package com.mvi.sharednotes.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun BaseOutlinedTextField(
    value: String,
    isLoading: Boolean,
    hasError: Boolean,
    errorMessage: String,
    label: String,
    placeholder: String,
    icon: ImageVector?,
    shape: RoundedCornerShape,
    keyboardOptions: KeyboardOptions,
    userInputHandler: (String) -> Unit = {},
    onDoneClickListener: () -> Unit = {},
    onNextClickListener: () -> Unit = {},
    modifier: Modifier
) {
    var content by remember { mutableStateOf(TextFieldValue(value)) }

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
        shape = shape,
        value = content,
        leadingIcon = { icon?.let { Icon(imageVector = icon, contentDescription = null) } },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = { onDoneClickListener() },
            onNext = { onNextClickListener() }),
        onValueChange = { newContent ->
            content = newContent
            userInputHandler(newContent.text)
        },
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) }
    )
}