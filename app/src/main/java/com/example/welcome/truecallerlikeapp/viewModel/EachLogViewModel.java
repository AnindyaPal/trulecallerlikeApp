package com.example.welcome.truecallerlikeapp.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.welcome.truecallerlikeapp.repository.callLogRepo.CallLogsRepository;
import com.example.welcome.truecallerlikeapp.repository.models.CallLogModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class EachLogViewModel extends AndroidViewModel {
    public MutableLiveData<List<CallLogModel>> callLogModelMutableLiveData;
    CallLogsRepository callLogsRepository;

    public EachLogViewModel(@NonNull Application application) {
        super(application);
        callLogModelMutableLiveData = new MutableLiveData<>();
        callLogsRepository = new CallLogsRepository(application.getApplicationContext());
    }

    public MutableLiveData<List<CallLogModel>> throwEachLog(){
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
