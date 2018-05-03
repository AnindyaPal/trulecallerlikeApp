package com.example.welcome.truecallerlikeapp.views.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.welcome.truecallerlikeapp.R;
import com.example.welcome.truecallerlikeapp.repository.models.CallLogModel;
import com.example.welcome.truecallerlikeapp.viewModel.EachLogViewModel;
import com.example.welcome.truecallerlikeapp.views.adapters.RecyclerAdapterLogs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallLogActivity extends AppCompatActivity {

    @BindView(R.id.rvCallLog)
    RecyclerView rvCallLog;

    RecyclerAdapterLogs recyclerAdapterLogs;

    List<CallLogModel> callLogs;

    EachLogViewModel eachLogViewModel;

    Observer<List<CallLogModel>> callLogModelObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        ButterKnife.bind(this);

        initMembers();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        eachLogViewModel.throwEachLog().removeObserver(callLogModelObserver);
    }

    private void initMembers() {
        callLogs = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerAdapterLogs = new RecyclerAdapterLogs(callLogs,this);
        rvCallLog.setAdapter(recyclerAdapterLogs);
        rvCallLog.setLayoutManager(linearLayoutManager);

        eachLogViewModel = ViewModelProviders.of(this).get(EachLogViewModel.class);

        callLogModelObserver = this::addItems;

        observeLiveData();
    }

    private void observeLiveData() {
        eachLogViewModel.throwEachLog().observe(this,callLogModelObserver);
    }

    public void addItems(List<CallLogModel> callLogModels){
        recyclerAdapterLogs.addLog(callLogModels);
    }

}
