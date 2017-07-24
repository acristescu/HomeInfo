package io.zenandroid.homeinfo.landing;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.zenandroid.homeinfo.R;
import io.zenandroid.homeinfo.util.EspressoIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by acristescu on 03/07/2017.
 */

@RunWith(AndroidJUnit4.class)
public class LandingActivityTest {

	@Rule
	public ActivityTestRule<LandingActivity> playlistActivityTestRule = new ActivityTestRule<>(LandingActivity.class);

	@Before
	public void registerIdlingResource() {
		Espresso.registerIdlingResources(EspressoIdlingResource.getInstance());
	}

	@After
	public void unregisterIdlingResource() {
		Espresso.unregisterIdlingResources(EspressoIdlingResource.getInstance());
	}

	@Test
	public void testActivityLoads() {
		onView(withId(R.id.text)).check(matches(withText("Mon:\t15°C - 20°C\tMostly cloudy throughout the day.\n" +
				"Tue:\t14°C - 24°C\tPartly cloudy until evening.\n" +
				"Wed:\t16°C - 22°C\tLight rain until evening.\n" +
				"Thu:\t14°C - 21°C\tLight rain starting in the afternoon, continuing until evening.\n" +
				"Fri:\t13°C - 22°C\tMostly cloudy throughout the day.\n" +
				"Sat:\t16°C - 23°C\tLight rain in the morning and overnight.\n" +
				"Sun:\t17°C - 22°C\tLight rain in the morning.\n" +
				"Mon:\t15°C - 22°C\tMostly cloudy starting in the afternoon.")));
	}
}
