package io.simplelogin.android

import android.accounts.Account
import androidx.appcompat.widget.AppCompatImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.idlewaiting.KautomatorWaitForIdleSettings
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.simplelogin.android.module.login.LoginActivity
import io.simplelogin.android.module.startup.StartupActivity
import io.simplelogin.android.screens.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BaseCheckTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        kautomatorWaitForIdleSettings = KautomatorWaitForIdleSettings.boost()
    }
) {

    @get:Rule
    var activityActivityTestRule: ActivityTestRule<StartupActivity> =
        ActivityTestRule(StartupActivity::class.java)

    @Test
    fun baseChecks() {
        LoginScreen {
            setEmail("alexsimplestar@gmail.com")
            setPassword("1234Qwer!")
            clickLoginButton()
        }
        AliasesScreen {
            shouldBeOpen()
            aliasCardShouldBeVisible()
            allTabSelected()
            activeTabUnselected()
            inactiveTabUnselected()
        }
        AliasesScreen {
            clickActiveTab()
            allTabUnselected()
            activeTabSelected()
            inactiveTabUnselected()
            aliasCardShouldBeVisible()
        }
        AliasesScreen {
            clickInactiveTab()
            allTabUnselected()
            activeTabUnselected()
            inactiveTabSelected()
            aliasCardShouldBeHidden()
        }
        AliasesScreen {
            clickAllTab()
            allTabSelected()
            activeTabUnselected()
            inactiveTabUnselected()
        }
        AliasesScreen {
            aliasSwitchShouldBeChecked()
            clickAliasSwitch()
            aliasSwitchShouldNotBeChecked()
            clickActiveTab()
            aliasCardShouldBeHidden()
            clickInactiveTab()
            aliasCardShouldBeVisible()
            clickAllTab()
            aliasCardShouldBeVisible()
            clickAliasSwitch()
            aliasSwitchShouldBeChecked()
        }
        AliasesScreen {
            clickActiveTab()
            aliasCardShouldBeVisible()
            clickInactiveTab()
            aliasCardShouldBeHidden()
            clickAllTab()
            aliasCardShouldBeVisible()
        }
        NavigationScreen{
            goToSettingAccount()
        }
        AccountSettingsScreen{
            shouldBeOpen()
            clickUpgradeButton()
        }
        UpgradeScreen{
            shouldBeOpen()
            clickBackIcon()
        }
        AccountSettingsScreen{
            shouldBeOpen()
        }
        NavigationScreen{
            goToAliases()
        }
        AliasesScreen {
            shouldBeOpen()
            aliasCardShouldBeVisible()
        }
        NavigationScreen{
            signOut()
        }
        LoginScreen{
            shouldBeOpen()
        }
    }

}
