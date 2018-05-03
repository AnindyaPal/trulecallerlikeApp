package com.example.welcome.truecallerlikeapp.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.welcome.truecallerlikeapp.ApplicationClass;
import com.example.welcome.truecallerlikeapp.di.components.DaggerViewmodelComponent;
import com.example.welcome.truecallerlikeapp.di.components.ViewmodelComponent;
import com.example.welcome.truecallerlikeapp.repository.callLogRepo.CallLogsRepository;
import com.example.welcome.truecallerlikeapp.repository.models.CallLogModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class EachLogViewModel extends AndroidViewModel {

    public MutableLiveData<List<CallLogModel>> callLogModelMutableLiveData;

    @Inject
    public CallLogsRepository callLogsRepository;

    ViewmodelComponent viewmodelComponent;

    public EachLogViewModel(@NonNull Application application) {
        super(application);
        callLogModelMutableLiveData = new MutableLiveData<>();
        viewmodelComponent = DaggerViewmodelComponent.builder()
                .applicationComponent(ApplicationClass.getApplication().getComponent()).build();
        viewmodelComponent.injectViewModel(this);
    }

    public MutableLiveData<List<CallLogModel>> throwLogsMutable(){
       new CompositeDisposable().add(Flowable.fromCallable(()->callLogsRepository.getAllCallLogs())
                                             .subscribeOn(Schedulers.newThread())
                                             .observeOn(Schedulers.newThread())
                                             .subscribeWith(new ResourceSubscriber<List<CallLogModel>>() {
                                                 @Override
                                                 public void onNext(List<CallLogModel> callLogModels) {
                                                         callLogModelMutableLiveData.postValue(callLogModels);
                                                 }

                                                 @Override
                                                 public void onError(Throwable t) {

                                                 }

                                                 @Override
                                                 public void onComplete() {

                                                 }
                                             }));

        return callLogModelMutableLiveData;
    }
}
