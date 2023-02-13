package com.example.tesi_gse;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.matcher.*;
import androidx.test.espresso.core.*;

import com.example.tesi_gse.Operation.GPSOperation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExecutionTest {

    @Rule
    public ActivityScenarioRule mMainActivityRule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void GPSOperationTest(){
        onView(ViewMatchers.withId(R.id.requestBtn)).perform(click());
    }

    @Test
    public void HTTPOperationTest(){
        onView(ViewMatchers.withId(R.id.requestHttp)).perform(click());
    }


}
