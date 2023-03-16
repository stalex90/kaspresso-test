package io.simplelogin.android.screens

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.simplelogin.android.module.login.LoginActivity
import io.simplelogin.android.R

object LoginScreen : KScreen<LoginScreen>() {

    override val layoutId: Int = R.layout.activity_login
    override val viewClass: Class<*> = LoginActivity::class.java

    val loginButton = KButton { withId(R.id.loginButton) }
    val emailEditTextField = KEditText { withId(R.id.emailEditTextField) }
    val passwordEditTextField = KEditText { withId(R.id.passwordEditTextField) }

    fun setEmail(str: String) {
        emailEditTextField {
            click()
            replaceText(str)
        }
    }

    fun setPassword(str: String) {
        passwordEditTextField {
            click()
            replaceText(str)
        }
    }

    fun clickLoginButton() {
        loginButton {
            click()
        }
    }

    fun shouldBeOpen() {
        emailEditTextField {
            isVisible()
        }
    }
}
