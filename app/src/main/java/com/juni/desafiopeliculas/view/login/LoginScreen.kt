package com.juni.desafiopeliculas.view.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen(loginViewModel: LoginViewModel, nextScreen: () -> Unit) {
    val email by loginViewModel.user.observeAsState(initial = "")
    val password by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable by loginViewModel.isButtonEnabled.observeAsState(initial = false)
    val isUserValidate by loginViewModel.isUserValidate.observeAsState(initial = false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            EmailField(email = email) {
                loginViewModel.onLoginChange(it, password)
            }
            Spacer(modifier = Modifier.size(8.dp))
            PasswordField(password = password) {
                loginViewModel.onLoginChange(email, it)
            }
            Spacer(modifier = Modifier.size(8.dp))
            LoginButton(isLoginEnable) {
                loginViewModel.onLoginSelected()
            }
        }
    }

    if (isUserValidate){
        nextScreen()
    }

}

@Composable
fun EmailField(email: String, dataUserChange: (String) -> Unit) {

    TextField(
        value = email,
        onValueChange = { dataUserChange(it) },
        placeholder = { Text("Ingresar Usuario") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun PasswordField(password: String, dataUserChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { dataUserChange(it) },
        placeholder = { Text("password") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun LoginButton(isEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        enabled = isEnable,
        onClick = { onLoginSelected() }) {
        Text(text = "iniciar")
    }
}