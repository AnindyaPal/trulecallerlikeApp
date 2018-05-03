package com.example.welcome.truecallerlikeapp;

import android.app.Application;

import com.example.welcome.truecallerlikeapp.di.components.ApplicationComponent;
import com.example.welcome.truecallerlikeapp.di.components.DaggerApplicationComponent;
import com.example.welcome.truecallerlikeapp.di.modules.CallLogRepoModule;
import com.example.welcome.truecallerlikeapp.di.modules.ContextModule;

public class ApplicationClass extends Application {
    public static ApplicationClass APPINSTANCE;

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        APPINSTANCE = this;
        component = DaggerApplicationComponent.builder()
                .callLogRepoModule(new CallLogRepoModule())
                .contextModule(new ContextModule(APPINSTANCE)).build();
        component.injectApplication(APPINSTANCE);
    }

    public static ApplicationClass getApplication() {
        return APPINSTANCE;
    }

    public ApplicationComponent getComponent() {
        return component;
    }

}
