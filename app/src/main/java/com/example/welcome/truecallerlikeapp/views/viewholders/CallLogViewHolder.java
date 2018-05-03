package com.example.welcome.truecallerlikeapp.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.welcome.truecallerlikeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallLogViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvPhnNo)
    public TextView tvPhNumber;

    @BindView(R.id.tvCallDate)
    public TextView tvCallTime;

    @BindView(R.id.tvCallDuration)
    public TextView tvCallDuration;

    @BindView(R.id.tvCallType)
    public TextView tvDir;

    public CallLogViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
