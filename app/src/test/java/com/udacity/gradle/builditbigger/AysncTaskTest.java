package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.util.Pair;

import org.junit.Test;

public class AysncTaskTest {
    @Test
    public void verifyAsyncTaskResult(){
        Context context = null;
        assert new EndpointsAsyncTask().execute(context).equals("Here is a joke from joke source");
    }
}
