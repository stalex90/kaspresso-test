package io.simplelogin.android.screens

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.switch.KSwitch
import io.github.kakaocup.kakao.tabs.KTabLayout
import io.github.kakaocup.kakao.text.KButton
import io.simplelogin.android.R
import io.simplelogin.android.module.alias.AliasListFragment

object AliasesScreen : KScreen<AliasesScreen>() {

    override val layoutId: Int = R.layout.fragment_alias_list
    override val viewClass: Class<*> = AliasListFragment::class.java

    val tabsPanel = KTabLayout { withId(R.id.tabLayout) }
    val rootRelativeLayout = KTabLayout { withId(R.id.rootRelativeLayout) }
    val allTabItem = KButton { withContentDescription("All") }
    val activeTabItem = KButton { withContentDescription("Active") }
    val inactiveTabItem = KButton { withContentDescription("Inactive") }
    val aliasSwitch = KSwitch { withId(R.id.enabledSwitch) }

    fun shouldBeOpen() {
        tabsPanel {
            isVisible()
        }
    }

    fun aliasCardShouldBeVisible() {
        rootRelativeLayout {
            isVisible()
        }
    }

    fun aliasCardShouldBeHidden() {
        rootRelativeLayout {
            doesNotExist()
        }
    }

    fun allTabSelected() {
        allTabItem {
            isSelected()
        }
    }

    fun activeTabSelected() {
        activeTabItem {
            isSelected()
        }
    }

    fun inactiveTabSelected() {
        inactiveTabItem {
            isSelected()
        }
    }

    fun allTabUnselected() {
        allTabItem {
            isNotSelected()
        }
    }

    fun activeTabUnselected() {
        activeTabItem {
            isNotSelected()
        }
    }

    fun inactiveTabUnselected() {
        inactiveTabItem {
            isNotSelected()
        }
    }

    fun clickAllTab() {
        allTabItem {
            click()
        }
    }

    fun clickActiveTab() {
        activeTabItem {
            click()
        }
    }

    fun clickInactiveTab() {
        inactiveTabItem {
            click()
        }
    }

    fun clickAliasSwitch() {
        aliasSwitch {
            click()
        }
    }

    fun aliasSwitchShouldBeChecked() {
        aliasSwitch {
            isChecked()
        }
    }

    fun aliasSwitchShouldNotBeChecked() {
        aliasSwitch {
            isNotChecked()
        }
    }

}
