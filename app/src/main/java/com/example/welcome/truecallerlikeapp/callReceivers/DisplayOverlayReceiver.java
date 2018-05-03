package com.example.welcome.truecallerlikeapp.callReceivers;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.welcome.truecallerlikeapp.R;
import com.example.welcome.truecallerlikeapp.views.ui.OverlayDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayOverlayReceiver extends PhoneCallReceiver {
    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        showSystemDialog(ctx);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        showSystemDialog(ctx);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        showSystemDialog(ctx);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        showSystemDialog(ctx);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        showSystemDialog(ctx);
    }

    private String getStartAsString(Date start) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String reportDate = df.format(start);
        return reportDate;
    }

    private void launchIntent(Context ctx, String number, Date start){
        Intent launch_intent = new  Intent(ctx.getApplicationContext(), OverlayDialog.class);
        //launch_intent.setClassName("com.example.welcome.truecallerlikeapp.views","com.example.welcome.truecallerlikeapp.views.ui.OverlayDialog");
        launch_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        launch_intent.putExtra("number", number);
        launch_intent.putExtra("date", getStartAsString(start));
        //launch_intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ctx.startActivity(launch_intent);
    }

    private void showSystemDialog(Context context) {
        final WindowManager manager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.alpha = 1.0f;
        layoutParams.packageName = context.getPackageName();
        layoutParams.buttonBrightness = 1f;
        layoutParams.windowAnimations = android.R.style.Animation_Dialog;

        final View view = View.inflate(context.getApplicationContext(), R.layout.activity_overlay_dialog, null);
        ImageView noButton =  view.findViewById(R.id.ivCross);
        noButton.setOnClickListener(v -> manager.removeView(view));
        if (manager != null) {
            manager.addView(view, layoutParams);
        }
    }

}
