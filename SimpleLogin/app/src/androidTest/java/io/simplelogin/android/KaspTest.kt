//package com.kaspersky.kaspressample.simple_tests
//
//import android.Manifest
//import androidx.test.rule.ActivityTestRule
//import androidx.test.rule.GrantPermissionRule
//import com.kaspersky.kaspressample.MainActivity
//import com.kaspersky.kaspressample.R
//import com.kaspersky.kaspressample.screen.MainScreen
//import com.kaspersky.kaspressample.screen.SimpleScreen
//import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
//import io.simplelogin.android.module.startup.StartupActivity
//import org.junit.Rule
//import org.junit.Test
//
///**
// * In this example you can observe a test tuned by default Kaspresso configuration.
// * When you start the test you can see output of default Kaspresso interceptors:
// * - a lot of useful logs
// * - failure handling
// * - screenshots in the device
// * * Also you can observe the test dsl simplifying a writing of any test
// */
//class SimpleTest : TestCase() {
//
//    @get:Rule
//    var activityActivityTestRule: ActivityTestRule<StartupActivity> =
//        ActivityTestRule(StartupActivity::class.java)
//
//    @Test
//    fun test() = run {
//        step("Open Simple Screen") {
//            testLogger.i("I am testLogger")
//            device.screenshots.take("Additional_screenshot")
//            MainScreen {
//                simpleButton {
//                    isVisible()
//                    click()
//                }
//            }
//        }
//
//        step("Click button_1 and check button_2") {
//            SimpleScreen {
//                button1 {
//                    click()
//                }
//                button2 {
//                    isVisible()
//                }
//            }
//        }
//
//        step("Click button_2 and check edit") {
//            SimpleScreen {
//                button2 {
//                    click()
//                }
//                edit {
//                    flakySafely(timeoutMs = 7000) { isVisible() }
//                    hasText(R.string.simple_fragment_text_edittext)
//                }
//            }
//        }
//
//        step("Check all possibilities of edit") {
//            scenario(
//                CheckEditScenario()
//            )
//        }
//    }
//}