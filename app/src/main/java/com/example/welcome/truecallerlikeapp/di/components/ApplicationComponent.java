package com.example.welcome.truecallerlikeapp.di.components;

import com.example.welcome.truecallerlikeapp.ApplicationClass;
import com.example.welcome.truecallerlikeapp.di.modules.CallLogRepoModule;
import com.example.welcome.truecallerlikeapp.di.scopes.AppScope;
import com.example.welcome.truecallerlikeapp.repository.callLogRepo.CallLogsRepository;

import dagger.Component;

@AppScope
@Component(modules = CallLogRepoModule.class)
public interface ApplicationComponent {
    void injectApplication(ApplicationClass appClass);
    CallLogsRepository getCallRepo();
}
