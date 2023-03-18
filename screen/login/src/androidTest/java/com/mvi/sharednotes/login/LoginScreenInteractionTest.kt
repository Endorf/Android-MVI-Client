package com.mvi.sharednotes.login

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mvi.sharednotes.login.UserInput.CORRECT_USER_INPUT
import com.mvi.sharednotes.login.UserInput.INCORRECT_USER_INPUT
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenInteractionTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loginScreen_errorDisplayed() {
        composeTestRule.apply {
            setContent {
                LoginScreen(viewModel()) {}
            }

            onNode(hasTestTag(EMAIL_TAG)).performTextInput(INCORRECT_USER_INPUT)

            onNode(hasTestTag(LOGIN_BUTTON_TAG)).performClick()

            composeTestRule.onNodeWithText(ERROR_MESSAGE).assertIsDisplayed()
        }
    }

    @Test
    fun loginScreen_successLogin() {
        composeTestRule.apply {
            setContent {
                LoginScreen(viewModel()) {}
            }

            onNode(hasTestTag(EMAIL_TAG)).performTextInput(CORRECT_USER_INPUT)

            onNode(hasTestTag(LOGIN_BUTTON_TAG)).performClick()
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "Invalid Email"
    }
}