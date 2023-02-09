package com.example.tesi_gse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

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

    }
}
