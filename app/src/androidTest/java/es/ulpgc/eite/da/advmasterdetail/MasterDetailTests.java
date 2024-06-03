package es.ulpgc.eite.da.advmasterdetail;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.content.res.Resources;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.da.advmasterdetail.categories.CategoryListActivity;


@SuppressWarnings("ALL")
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MasterDetailTests {

  @Rule
  public ActivityTestRule<CategoryListActivity> testRule =
      new ActivityTestRule<>(CategoryListActivity.class);

  @Test
  public void appTest() {

    onView(new RecyclerViewMatcher(R.id.category_recycler)
        .atPositionOnView(0, R.id.category_name))
        .check(matches(withText("Tenerife")));

    onView(new RecyclerViewMatcher(R.id.category_recycler)
        .atPositionOnView(5, R.id.category_name))
        .check(matches(withText("La Gomera")));


    ViewInteraction recyclerView1 =
        onView(new RecyclerViewMatcher(R.id.category_recycler)
            .atPositionOnView(2, R.id.category_name))
            .check(matches(withText("Gran Canaria")));
    recyclerView1.perform(click());

    onView(new RecyclerViewMatcher(R.id.product_recycler)
        .atPositionOnView(0, R.id.product_name))
        .check(matches(withText("Beaches of Gran Canaria")));

    onView(new RecyclerViewMatcher(R.id.product_recycler)
        .atPositionOnView(1, R.id.product_name))
        .check(matches(withText("Las Palmas de Gran Canaria")));


    pressBack();


    onView(new RecyclerViewMatcher(R.id.category_recycler)
        .atPositionOnView(0, R.id.category_name))
        .check(matches(withText("Tenerife")));


    ViewInteraction recyclerView2 =
        onView(new RecyclerViewMatcher(R.id.category_recycler)
            .atPositionOnView(4, R.id.category_name))
            .check(matches(withText("La Palma")));
    recyclerView2.perform(click());


    onView(new RecyclerViewMatcher(R.id.product_recycler)
        .atPositionOnView(1, R.id.product_name))
        .check(matches(withText("Santa Cruz de la Palma")));

    ViewInteraction recyclerView3 =
        onView(new RecyclerViewMatcher(R.id.product_recycler)
          .atPositionOnView(0, R.id.product_name))
          .check(matches(withText("Caldera de Taburiente National Park")));
    recyclerView3.perform(click());

    ViewInteraction textView15 = onView(allOf(
        withId(R.id.product_detail),
        isDisplayed()
    ));
    textView15.check(matches(withText(
        "Known as the Isla Bonita (Beautiful Island), " +
        "La Palma is the greenest of the Canary Islands. " +
        "Designated a UNESCO Biosphere Reserve, " +
        "La Palma's landscape varies from pristine forests " +
        "to sheer cliffs and black-sand beaches. " +
        "Among its many protected environments is " +
        "the Caldera de Taburiente National Park, " +
        "where volcanic peaks rise to 2,400 meters, " +
        "and lava flows descend to the sea. " +
        "For those in search of idyllic surroundings, " +
        "the park has wooded areas with streams and waterfalls. " +
        "Along the rocky coastline, picturesque little bays " +
        "are hidden away in between steep hillsides."
    )));


    pressBack();

    onView(new RecyclerViewMatcher(R.id.product_recycler)
        .atPositionOnView(1, R.id.product_name))
        .check(matches(withText("Santa Cruz de la Palma")));

    pressBack();

    onView(new RecyclerViewMatcher(R.id.category_recycler)
        .atPositionOnView(0, R.id.category_name))
        .check(matches(withText("Tenerife")));

    onView(new RecyclerViewMatcher(R.id.category_recycler)
        .atPositionOnView(5, R.id.category_name))
        .check(matches(withText("La Gomera")));
  }


}
