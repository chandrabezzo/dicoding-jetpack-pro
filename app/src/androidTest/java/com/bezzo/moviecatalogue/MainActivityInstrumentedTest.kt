package com.bezzo.moviecatalogue

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.bezzo.moviecatalogue.features.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun getMovie(){
        onView(withId(R.id.bnv_main)).check(matches(isDisplayed()))
        onView(withId(R.id.tl_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_movie)).check(matches(isSelected()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun gotoAbout(){
        onView(withId(R.id.bnv_main)).check(matches(isDisplayed()))
        onView(withId(R.id.tl_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_movie)).check(matches(isSelected()))
        onView(withId(R.id.nav_about)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_about)).perform(click())
    }
}