package com.mvi.sharednotes.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseButtonTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun button_creation() {
        composeTestRule.apply {
            setContent {
                BaseButton(
                    isLoading = true,
                    TITLE,
                    {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(BUTTON_TAG)
                )
            }

            onNode(hasTestTag(BUTTON_TAG)).assertExists()
        }
    }

    companion object {
        private const val TITLE = "title"

        private const val BUTTON_TAG = "button_tag"
    }
}