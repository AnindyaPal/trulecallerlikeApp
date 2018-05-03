package com.example.welcome.truecallerlikeapp.di.modules;

import com.example.welcome.truecallerlikeapp.ApplicationClass;
import com.example.welcome.truecallerlikeapp.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    public ApplicationClass appClass;

    public ContextModule(ApplicationClass appClass){
        this.appClass = appClass;
    }
    @AppScope
    @Provides
    public ApplicationClass getContext(){
        return appClass;
    }
}
