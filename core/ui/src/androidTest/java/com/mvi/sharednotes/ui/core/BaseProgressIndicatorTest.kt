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
class BaseProgressIndicatorTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun progressView_isVisible() {
        composeTestRule.apply {
            setContent {
                BaseProgressIndicator(
                    isLoading = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(PROGRESS)
                )
            }

            onNode(hasTestTag(PROGRESS)).assertExists()
        }
    }

    @Test
    fun progressView_isNotVisible() {
        composeTestRule.apply {
            setContent {
                BaseProgressIndicator(
                    isLoading = false,
                    modifier = Modifier.testTag(PROGRESS)
                )
            }

            onNode(hasTestTag(PROGRESS)).assertDoesNotExist()
        }
    }

    companion object {
        private const val PROGRESS = "progress_indicator"
    }
}