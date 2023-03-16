package io.simplelogin.android.screens

import androidx.appcompat.widget.AppCompatImageButton
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.navigation.KNavigationView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.simplelogin.android.module.login.LoginActivity
import io.simplelogin.android.R
import io.simplelogin.android.module.home.HomeActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
object NavigationScreen : KScreen<NavigationScreen>() {

    override val layoutId: Int = R.layout.activity_home
    override val viewClass: Class<*> = HomeActivity::class.java

    val settingsMenuItem = KButton { withText("Settings") }
    val aliasMenuItem = KButton { withText("Aliases") }
    val signOutMenuItem = KButton { withText("Sign Out") }
    val confirmButton = KButton { withText("Yes, sign me out") }

    fun goToSettingAccount() {
        Espresso.onView(
            allOf(
                instanceOf(AppCompatImageButton::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        )
            .perform(ViewActions.click())
        settingsMenuItem {
            click()
        }
    }

    fun goToAliases() {
        Espresso.onView(
            allOf(
                instanceOf(AppCompatImageButton::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        )
            .perform(ViewActions.click())
        aliasMenuItem {
            click()
        }
    }

    fun signOut() {
        Espresso.onView(
            allOf(
                instanceOf(AppCompatImageButton::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        )
            .perform(ViewActions.click())
        signOutMenuItem {
            click()
        }
        confirmButton {
            click()
        }
    }
}
