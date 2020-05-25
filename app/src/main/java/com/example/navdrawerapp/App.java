package com.example.navdrawerapp;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ohCbbQa1boTxLvouRGz7cGO8tdDHP7kKd5gUD4Vf")
                // if defined
                .clientKey("o0bMLZVFeQmXwzTgrbsCNm79VfDuVDEvqnDVcMNw")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
