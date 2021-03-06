package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.jokeandroidlib.JokeActivity;
import com.udacity.gradle.jokeandroidlib.JokeActivityFragment;

import java.io.IOException;

//code template gotten from github repo at GoogleCloudPlatform/gradle-appengine-templates/HelloEndpoints
class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... contextt) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();

        }

        context = contextt[0];

        try {
            return myApiService.getJokeFromSource().execute().getData();
        } catch (IOException e) {
            Log.d("AsyncTaskError", e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivityFragment.JOKE_EXTRA, joke);
        context.startActivity(intent);
    }
}
