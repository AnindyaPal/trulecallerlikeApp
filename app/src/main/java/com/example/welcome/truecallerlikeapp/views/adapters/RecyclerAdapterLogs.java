package com.example.welcome.truecallerlikeapp.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.welcome.truecallerlikeapp.R;
import com.example.welcome.truecallerlikeapp.repository.models.CallLogModel;
import com.example.welcome.truecallerlikeapp.views.viewholders.CallLogViewHolder;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.sql.Struct;
import java.util.List;

public class RecyclerAdapterLogs extends RecyclerView.Adapter<CallLogViewHolder> implements FastScrollRecyclerView.SectionedAdapter {

    private List<CallLogModel> callLogs;
    private Context mContext;

    public RecyclerAdapterLogs(List<CallLogModel> callLogs, Context context){
        this.mContext = context;
        this.callLogs = callLogs;
    }

    @NonNull
    @Override
    public CallLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_log_item, parent, false);
        return new CallLogViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogViewHolder holder, int position) {
          CallLogModel callLogModel = callLogs.get(position);
          String duration = callLogModel.getCallDuration()+" seconds";
          holder.tvCallDuration.setText(duration);
          holder.tvPhNumber.setText(callLogModel.getPhNumber());
          holder.tvDir.setText(callLogModel.getDir());
          holder.tvCallTime.setText(callLogModel.getCallDayTime());
    }

    @Override
    public int getItemCount() {
        return callLogs.size();
    }

    public void addLog(List<CallLogModel> callLogModels){
        this.callLogs.addAll(callLogModels);
        notifyItemRangeInserted(0,callLogModels.size());
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return "";
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
