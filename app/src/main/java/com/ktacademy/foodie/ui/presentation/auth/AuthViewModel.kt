package com.ktacademy.foodie.ui.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktacademy.foodie.data.network.AppResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class  AuthViewModel : ViewModel() {

    val authUIState = MutableStateFlow(AuthUIState())
    val screenState: MutableStateFlow<AppResource<String>?> = MutableStateFlow(null)

    fun handleLogin(actions: AuthActions){
        when(actions){
            is AuthActions.EmailChange -> {
                authUIState.update {
                    it.copy(email = actions.email)
                }
            }
            AuthActions.SubmitAuth -> {
                authUIState.update {
                    viewModelScope.launch {
                        screenState.emit(AppResource.Loading())
                        delay(5000)
                        screenState.emit(AppResource.Success(body = "", status = "Success"))
                    }
                    AuthUIState()
                }
            }
        }
    }

}

data class AuthUIState(
    val email: String = ""
){
    fun valid(): Boolean = email.length > 5
}

sealed class AuthActions {
    data class EmailChange(val email: String): AuthActions()
    object SubmitAuth: AuthActions()
}