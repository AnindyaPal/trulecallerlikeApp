package com.example.welcome.truecallerlikeapp.views.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    RecyclerAdapterLogs recyclerAdapterLogs;

    List<CallLogModel> callLogs;

    EachLogViewModel eachLogViewModel;

    Observer<List<CallLogModel>> callLogModelObserver;

    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE= 5469;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        checkPermission();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this,"Please allow permission,else you cant see the caller dialog",Toast.LENGTH_LONG).show();
                    checkPermission();
                }
                else {
                    onResume();
                }
            }
        }
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
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
        progressBar.setVisibility(View.GONE);
        recyclerAdapterLogs.addLog(callLogModels);
    }

}
