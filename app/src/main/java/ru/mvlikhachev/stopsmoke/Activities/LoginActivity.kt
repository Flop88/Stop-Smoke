package ru.mvlikhachev.stopsmoke.Activities


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import ru.mvlikhachev.stopsmoke.R


class LoginActivity : AppCompatActivity() {

    private var isLoginModeActive: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        // first authorization
        authorizationUi()
    }

    fun toggleLoginSignUp(view: View) {
        if (isLoginModeActive) {
            registarionUi();
        } else {
            authorizationUi();
        }

    }

    private fun authorizationUi() {
        isLoginModeActive = true
        loginSignUpButton.setText("Войти")
        toggleLoginSignUpTextView.setText("Или зарегистрируйтесь")
        textInputConfirmPassword.visibility = View.GONE
        textInputName.visibility = View.GONE
    }

    private fun registarionUi() {
        isLoginModeActive = false
        loginSignUpButton.setText("Зарегистрироваться")
        toggleLoginSignUpTextView.setText("Или авторизуйтесь")
        textInputConfirmPassword.visibility = View.VISIBLE
        textInputName.visibility = View.VISIBLE
    }

    // Validation

    private fun validateEmail(): Boolean {
        val emailInput = textInputEmail
            .editText
            ?.getText()
            .toString()
            .trim()
        return if (emailInput.isEmpty()) {
            textInputEmail.error = "Введите email!"
            false
        } else {
            textInputEmail.error = ""
            true
        }
    }

    private fun validateName(): Boolean {
        val nameInput = textInputName
            .editText
            ?.getText()
            .toString()
            .trim()
        return if (nameInput.isEmpty()) {
            textInputName.error = "Введите Ваше имя!"
            false
        } else if (nameInput.length > 10) {
            textInputName.error = "Имя должно быть меньше 15 символов!"
            false
        } else {
            textInputName.error = ""
            true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput = textInputPassword
            .editText
            ?.getText()
            .toString()
            .trim()
        return if (passwordInput.isEmpty()) {
            textInputPassword.error = "Введите Ваше имя!"
            false
        } else if (passwordInput.length < 6) {
            textInputPassword.error = "Пароль должно быть больше 6 символов!"
            false
        } else {
            textInputPassword.error = ""
            true
        }
    }

    private fun validateConfirmPassword(): Boolean {
        val passwordInput = textInputPassword
            .editText
            ?.getText()
            .toString()
            .trim()
        val confirmPasswordInput = textInputConfirmPassword
            .editText
            ?.getText()
            .toString()
            .trim()
        return if (passwordInput != confirmPasswordInput) {
            textInputPassword.error = "Пароли должны совпадать"
            false
        } else {
            textInputPassword.error = ""
            true
        }
    }




    fun loginSignUpUser(view: View) {

        if (!validateEmail() or !validatePassword()) {
            return
        }
        if (isLoginModeActive) {
            Log.d("clickButton", "Log in")
        } else {
            Log.d("clickButton", "Registration")
        }
    }
}