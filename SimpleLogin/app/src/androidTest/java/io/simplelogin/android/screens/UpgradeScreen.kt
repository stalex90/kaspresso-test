package io.simplelogin.android.screens

import androidx.appcompat.widget.AppCompatImageButton
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.simplelogin.android.module.login.LoginActivity
import io.simplelogin.android.R
import io.simplelogin.android.module.settings.SettingsFragment
import org.hamcrest.CoreMatchers

object UpgradeScreen : KScreen<UpgradeScreen>() {

    override val layoutId: Int = R.layout.layout_profile_info_card_view
    override val viewClass: Class<*> = SettingsFragment::class.java

    val updateButton = KButton { withId(R.id.upgradeButton) }

    fun shouldBeOpen() {
        updateButton {
            isVisible()
        }
    }

    fun clickBackIcon() {
        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(AppCompatImageButton::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        )
            .perform(ViewActions.click())
    }
}
