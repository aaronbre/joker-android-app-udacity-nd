import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.JokeResponseIdlingResource;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.paid.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    @Rule
    public ActivityTestRule<MainActivity> mIntentsTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void asyncTaskReturnsResult(){
        onView(withId(R.id.tell_joke_button)).perform(click());
        onView(withId(R.id.joke_question)).check(matches(not(withText(""))));
        onView(withId(R.id.joke_answer)).check(matches(withText("")));
        JokeResponseIdlingResource idlingResource = new JokeResponseIdlingResource(3000);
        IdlingRegistry.getInstance().register(idlingResource);
        onView(withId(R.id.joke_answer)).check(matches(not(withText(""))));
        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}
