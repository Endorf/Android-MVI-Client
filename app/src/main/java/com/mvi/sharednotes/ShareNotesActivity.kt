package com.mvi.sharednotes

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mvi.sharednotes.theme.SharedNotesTheme
import com.mvi.sharednotes.view.InitialViewModel
import com.mvi.sharednotes.view.attributes.State
import com.mvi.sharednotes.view.ui.SharedNotesApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShareNotesActivity : ComponentActivity() {

    private val viewModel: InitialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        var state: State by mutableStateOf(
            State(
                isLoading = true,
                hasError = false,
                isUserAuthenticated = false
            )
        )

        val splashScreen = applySplashScreen()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state
                    .onEach {
                        state = it
                    }
                    .collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when {
                state.isLoading -> true
                else -> false
            }
        }
        setContent {
            SharedNotesTheme {
                SharedNotesApp(state)
            }
        }
    }

    private fun applySplashScreen() = installSplashScreen().apply {
        setOnExitAnimationListener {
            val fadeAnim = ObjectAnimator.ofFloat(
                it.view,
                View.ALPHA,
                1f,
                0f
            )
            fadeAnim.duration = ANIMATION_DURATION
            fadeAnim.interpolator = AccelerateInterpolator()
            fadeAnim.start()
        }
    }

    companion object {
        private const val ANIMATION_DURATION = 300L
    }
}