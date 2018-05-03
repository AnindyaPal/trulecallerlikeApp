package com.example.welcome.truecallerlikeapp.di.modules;
import com.example.welcome.truecallerlikeapp.ApplicationClass;
import com.example.welcome.truecallerlikeapp.di.scopes.AppScope;
import com.example.welcome.truecallerlikeapp.repository.callLogRepo.CallLogsRepository;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class CallLogRepoModule {
    @AppScope
    @Provides
    public CallLogsRepository getCallLogsRepo(ApplicationClass applicationClass){
        return new CallLogsRepository(applicationClass);
    }
}
