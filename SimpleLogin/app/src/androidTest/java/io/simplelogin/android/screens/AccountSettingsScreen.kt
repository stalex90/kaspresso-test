package io.simplelogin.android.screens

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.simplelogin.android.module.login.LoginActivity
import io.simplelogin.android.R
import io.simplelogin.android.module.settings.SettingsFragment

object AccountSettingsScreen : KScreen<AccountSettingsScreen>() {

    override val layoutId: Int = R.layout.layout_profile_info_card_view
    override val viewClass: Class<*> = SettingsFragment::class.java

    val updateButton = KButton { withId(R.id.upgrade_text_view) }

    fun shouldBeOpen() {
        updateButton {
            isVisible()
        }
    }

    fun clickUpgradeButton() {
        updateButton {
            click()
        }
    }
}
