package com.mvi.sharednotes.login

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mvi.sharednotes.login.UserInput.CORRECT_USER_INPUT
import com.mvi.sharednotes.login.UserInput.INCORRECT_USER_INPUT
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loginScreen() {
        composeTestRule.setContent {
            LoginScreen(viewModel()) {}
        }
        val buttonTitle = composeTestRule.activity.getString(R.string.login_button_title)
        composeTestRule.onNodeWithText(buttonTitle).assertIsDisplayed()

        composeTestRule.onNodeWithTag(EMAIL_TAG).assertIsDisplayed()
    }

    @Test
    fun onRecreationLoginScreen_stateIsRestored() {
        val restorationTester = StateRestorationTester(composeTestRule)

        restorationTester.setContent { LoginScreen(viewModel()) {} }

        composeTestRule.onNodeWithTag(EMAIL_TAG).performTextInput(INCORRECT_USER_INPUT)

        restorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithTag(EMAIL_TAG).assert(hasText(CORRECT_USER_INPUT))
    }
}