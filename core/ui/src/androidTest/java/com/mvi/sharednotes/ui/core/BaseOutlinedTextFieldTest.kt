package com.mvi.sharednotes.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseOutlinedTextFieldTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun textField_creation() {
        composeTestRule.apply {
            createTestContent(
                Modifier
                    .fillMaxWidth()
                    .testTag(TEXT_TAG)
            )

            onNode(hasTestTag(TEXT_TAG)).assertIsDisplayed()
            onNode(hasTestTag(TEXT_TAG)).assertIsEnabled()
        }
    }

    @Test
    fun textField_customValue() {
        composeTestRule.apply {
            createTestContent(
                value = TEXT,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TEXT_TAG)
            )

            onNodeWithText(TEXT).assertIsDisplayed()
        }
    }

    companion object {
        private const val TEXT = "text"
        private const val LABEL = "label"
        private const val PLACEHOLDER = "placeholder"

        private const val TEXT_TAG = "text_tag"

        private fun ComposeContentTestRule.createTestContent(
            modifier: Modifier,
            value: String = "",
            isLoading: Boolean = false,
            hasError: Boolean = false,
            label: String = LABEL,
            placeholder: String = PLACEHOLDER,
            icon: ImageVector? = null,
            userInputHandler: (String) -> Unit = {},
            onDoneClickListener: () -> Unit = {}
        ) = setContent {
            BaseOutlinedTextField(
                value,
                isLoading,
                hasError,
                label,
                placeholder,
                icon,
                userInputHandler,
                onDoneClickListener,
                modifier
            )
        }
    }
}