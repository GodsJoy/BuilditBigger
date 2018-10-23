package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.udacity.gradle.jokessource.JokesSource;

//code template gotten from github repo at GoogleCloudPlatform/gradle-appengine-templates/HelloEndpoints
/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "getJokeFromSource")
    public MyBean getJokeFromSource(){
        MyBean response = new MyBean();
        String joke = new JokesSource().getJoke();
        response.setData(joke);

        return response;
    }



}