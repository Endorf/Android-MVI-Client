package com.mvi.sharednotes.login

import com.mvi.sharednotes.login.data.FakeRepository
import com.mvi.sharednotes.login.view.LoginViewModel
import com.mvi.sharednotes.login.view.attributes.Event
import com.mvi.sharednotes.login.view.components.Reducer
import com.mvi.sharednotes.login.view.components.middleware.LoggerMiddleware
import com.mvi.sharednotes.login.view.components.middleware.Middleware
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.Spy

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @Spy
    lateinit var reducer: Reducer

    @Mock
    lateinit var repository: FakeRepository

    @Mock
    lateinit var logger: LoggerMiddleware

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockitoAnnotations.openMocks(this)

        val middleware = Middleware(repository, logger)

        viewModel = LoginViewModel(
            reducer,
            middleware
        )
    }

    @Test
    fun viewModel_Initialization() {
        val state = viewModel.state.value
        val effect = viewModel.effect.value

        assertEquals(state.email, "")
        assertFalse(state.isLoading)
        assertFalse(state.hasError)
        assertFalse(state.isSigned)

        assertFalse(effect.transitionNotes)
    }

    @Test
    fun emailUpdateEvent() = runTest {
        `when`(repository.doAuth(any())).thenReturn(USER_INPUT_CORRECT)

        viewModel.dispatch(Event.EmailUpdate(USER_INPUT_CORRECT))

        val state = viewModel.state.value
        assertEquals(state.email, USER_INPUT_CORRECT)
        assertFalse(state.isLoading)
        assertFalse(state.hasError)
        assertFalse(state.isSigned)
    }

    @Test
    fun loginEvent_Success() = runTest {
        `when`(repository.doAuth(any())).thenReturn(USER_INPUT_CORRECT)

        viewModel.dispatch(Event.EmailUpdate(USER_INPUT_CORRECT))

        viewModel.dispatch(Event.Login)

        val state = viewModel.state.value
        assertEquals(state.email, USER_INPUT_CORRECT)
        assertFalse(state.isLoading)
        assertFalse(state.hasError)
        assertTrue(state.isSigned)
    }

    @Test
    fun loginEvent_Failed() = runTest {
        `when`(repository.doAuth(any())).thenReturn(USER_INPUT_INCORRECT)

        viewModel.dispatch(Event.EmailUpdate(USER_INPUT_INCORRECT))

        viewModel.dispatch(Event.Login)

        val state = viewModel.state.value
        assertEquals(state.email, USER_INPUT_INCORRECT)
        assertFalse(state.isLoading)
        assertTrue(state.hasError)
        assertFalse(state.isSigned)
    }

    companion object {
        private const val USER_INPUT_CORRECT = "ema@il.com"
        private const val USER_INPUT_INCORRECT = "email"
    }
}