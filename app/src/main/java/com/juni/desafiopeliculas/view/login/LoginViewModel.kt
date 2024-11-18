package com.juni.desafiopeliculas.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    private var _user = MutableLiveData<String>()
    var user: LiveData<String> = _user

    private var _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    private var _isButtonEnabled = MutableLiveData<Boolean>()
    var isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private var _isUserValidate = MutableLiveData<Boolean>()
    var isUserValidate: LiveData<Boolean> = _isUserValidate

    fun onLoginChange(email: String, passWord: String) {
        _user.value = email
        _password.value = passWord
        _isButtonEnabled.value = enabledButton(email, passWord)
    }

    private fun enabledButton(email: String, password: String): Boolean {
        val emailPattern = Regex("^[a-zA-Z]+$")
        return emailPattern.matches(email) && password.length > 6
    }


    fun onLoginSelected() {
        viewModelScope.launch {
            val result = checkUser(user.value!!, password.value!!)
            _isUserValidate.value = result
        }
    }

    private fun checkUser(email: String, passWord: String): Boolean {
        return email == "Admin" && passWord == "1234567"
    }
}