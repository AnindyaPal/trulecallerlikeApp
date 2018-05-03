package com.example.welcome.truecallerlikeapp.callReceivers;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.welcome.truecallerlikeapp.R;
import com.example.welcome.truecallerlikeapp.views.ui.OverlayDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayOverlayReceiver extends PhoneCallReceiver {
    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        showSystemOverlay(ctx,number,start);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        showSystemOverlay(ctx,number,start);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        showSystemOverlay(ctx,number,start);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        showSystemOverlay(ctx,number,start);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        showSystemOverlay(ctx,number,start);
    }

    private String getStartAsString(Date start) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String reportDate = df.format(start);
        return reportDate;
    }

    public void showSystemOverlay(Context context, String number, Date start){
        final WindowManager manager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.RIGHT | Gravity.TOP;
        params.setTitle("Load Average");

        final View view = View.inflate(context.getApplicationContext(), R.layout.activity_overlay_dialog, null);
        ImageView noButton =  view.findViewById(R.id.ivCross);
        TextView tvDate    = view.findViewById(R.id.tvDate);
        TextView tvPhnNo   = view.findViewById(R.id.tvPhoneNo);

        tvDate.setText(getStartAsString(start));
        tvPhnNo.setText(number);
        noButton.setImageResource(R.drawable.cross);

        noButton.setOnClickListener(v ->
                manager.removeView(view)
        );

        manager.addView(view, params);
    }

}
