package com.mvi.sharednotes.login.view.components.middleware

import android.util.Log
import com.mvi.sharednotes.login.view.attributes.Action

class LoggerMiddleware {

    fun dispatch(action: Action) {
        Log.e("Login-Logger", "$action")
    }
}